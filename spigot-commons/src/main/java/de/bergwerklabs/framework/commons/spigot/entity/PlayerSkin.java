package de.bergwerklabs.framework.commons.spigot.entity;

import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedSignedProperty;
import de.bergwerklabs.framework.commons.spigot.general.reflection.LabsReflection;
import java.lang.reflect.Method;
import java.util.Optional;
import org.bukkit.entity.Player;

/**
 * Created by Yannic Rieger on 20.07.2017.
 *
 * <p>Wrapper for texture data needed to change the skin of NPCs or other players.
 *
 * @author Yannic Rieger
 */
public class PlayerSkin {

  private String value;
  private String signature;

  /**
   * @param value Base64 string
   * @param signature Base64 string; signed data using Yggdrasil's private key
   */
  public PlayerSkin(String value, String signature) {
    this.value = value;
    this.signature = signature;
  }

  /**
   * Gets the a texture data of a {@link Player}.
   *
   * @param player player to get the texture data from.
   * @return an {@link Optional} of {@link PlayerSkin} containing texture data.
   */
  public static Optional<PlayerSkin> fromPlayer(Player player) {
    try {
      Object handle = LabsReflection.getHandle(player);
      Method getProfile = LabsReflection.getMethod(handle.getClass(), "getProfile");

      Object profile = getProfile.invoke(handle);
      Method getProperties = LabsReflection.getMethod(profile.getClass(), "getProperties");

      Object properties = getProperties.invoke(profile);
      Object textures =
          LabsReflection.getMethod(properties.getClass(), "get", Object.class)
              .invoke(properties, "textures");
      Object iterator = LabsReflection.getMethod(textures.getClass(), "iterator").invoke(textures);
      Object property = LabsReflection.getMethod(iterator.getClass(), "next").invoke(iterator);
      Object signature =
          LabsReflection.getMethod(property.getClass(), "getSignature").invoke(property);
      Object value = LabsReflection.getMethod(property.getClass(), "getValue").invoke(property);

      return Optional.of(new PlayerSkin((String) value, (String) signature));
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return Optional.empty();
  }

  /** Base64 string */
  public String getValue() {
    return value;
  }

  /** Base64 string; signed data using Yggdrasil's private key */
  public String getSignature() {
    return signature;
  }

  /**
   * Injects the texture data into the {@link WrappedGameProfile}.
   *
   * @param gameProfile profile to inject the texture data.
   */
  public void inject(WrappedGameProfile gameProfile) {
    gameProfile.getProperties().removeAll("textures");
    gameProfile
        .getProperties()
        .put(
            "textures",
            new WrappedSignedProperty("textures", this.getValue(), this.getSignature()));
  }
}

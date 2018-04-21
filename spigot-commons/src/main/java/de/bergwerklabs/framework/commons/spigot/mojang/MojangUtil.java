package de.bergwerklabs.framework.commons.spigot.mojang;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.util.UUID;

/**
 * Created by Yannic Rieger on 28.04.2017.
 *
 * <p>Util class for Mojang specific tasks.
 *
 * @author Yannic Rieger
 */
public class MojangUtil {

  /**
   * Creates a non player game profile with the given Base64 encoded texture string.
   *
   * @param texture Base64 encoded string.
   * @return non player GameProfile with custom texture.
   */
  public static GameProfile getNonPlayerProfile(String texture) {
    GameProfile profile = new GameProfile(UUID.randomUUID(), null);
    profile.getProperties().clear();
    profile.getProperties().put("textures", new Property("textures", texture));
    return profile;
  }
}

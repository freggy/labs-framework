package de.bergwerklabs.framework.commons.spigot.entity.npc;

import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedSignedProperty;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import de.bergwerklabs.framework.commons.spigot.general.reflection.LabsReflection;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Yannic Rieger on 20.07.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class PlayerSkin {

    private String value;
    private String signature;

    public PlayerSkin(String value, String signature) {
        this.value = value;
        this.signature = signature;
    }

    public String getValue() {
        return value;
    }

    public String getSignature() {
        return signature;
    }

    public void inject(WrappedGameProfile gameProfile) {
        gameProfile.getProperties().removeAll("textures");
        gameProfile.getProperties().put("textures", new WrappedSignedProperty("textures", this.getValue(), this.getSignature()));
    }

    public static PlayerSkin fromPlayer(Player player) {
        try {
            Object handle = LabsReflection.getHandle(player);
            Method getProfile = LabsReflection.getMethod(handle.getClass(), "getProfile");

            Object profile = getProfile.invoke(handle);
            Method getProperties = LabsReflection.getMethod(profile.getClass(), "getProperties");

            Object properties = getProperties.invoke(profile);
            Object textures = LabsReflection.getMethod(properties.getClass(), "get", Object.class).invoke(properties, "textures");
            Object iterator = LabsReflection.getMethod(textures.getClass(), "iterator").invoke(textures);
            Object property = LabsReflection.getMethod(iterator.getClass(), "next").invoke(iterator);
            Object signature = LabsReflection.getMethod(property.getClass(), "getSignature").invoke(property);
            Object value = LabsReflection.getMethod(property.getClass(), "getValue").invoke(property);

            return new PlayerSkin((String)value, (String)signature);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

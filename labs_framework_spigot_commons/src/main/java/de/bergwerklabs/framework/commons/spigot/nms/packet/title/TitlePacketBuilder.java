package de.bergwerklabs.framework.commons.spigot.nms.packet.title;

import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import de.bergwerklabs.framework.commons.spigot.general.reflection.LabsReflection;
import de.bergwerklabs.framework.commons.spigot.nms.MinecraftVersion;
import de.bergwerklabs.framework.commons.spigot.nms.packet.PacketBuilder;

/**
 * Created by Yannic Rieger on 18.07.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class TitlePacketBuilder extends PacketBuilder<TitlePacket> {

    public TitlePacketBuilder(MinecraftVersion version) {
        this.assignPacketClass(version, "title", "WrapperPlayServerTitle");
    }

    public TitlePacketBuilder() {
        this.assignPacketClass(MinecraftVersion.formString(LabsReflection.getVersion()), "title", "WrapperPlayServerTitle");
    }

    public TitlePacketBuilder setFadeIn(int fadeIn) {
        this.packet.setFadeIn(fadeIn);
        return this;
    }

    public TitlePacketBuilder setFadeOut(int fadeIn) {
        this.packet.setFadeOut(fadeIn);
        return this;
    }

    public TitlePacketBuilder setStay(int fadeIn) {
        this.packet.setStay(fadeIn);
        return this;
    }

    public TitlePacketBuilder setTitle(WrappedChatComponent chatComponent) {
        this.setTitle(chatComponent);
        return this;
    }

    public TitlePacketBuilder setAction(EnumWrappers.TitleAction action) {
        this.setAction(action);
        return this;
    }
}

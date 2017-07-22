package de.bergwerklabs.framework.commons.spigot.nms.packet.serverside.title;

import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import de.bergwerklabs.framework.commons.spigot.nms.packet.Packet;

/**
 * Created by Yannic Rieger on 18.07.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public interface TitlePacket extends Packet {

    EnumWrappers.TitleAction getAction();

    WrappedChatComponent getTitle();

    int getFadeIn();

    int getFadeOut();

    int getStay();

    void setAction(EnumWrappers.TitleAction action);

    void setTitle(WrappedChatComponent value);

    void setFadeIn(int fadeIn);

    void setFadeOut(int fadeOut);

    void setStay(int stay);
}

package de.bergwerklabs.framework.commons.spigot.nms.packet.serverside.title;

import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import de.bergwerklabs.framework.commons.spigot.nms.packet.Packet;

/**
 * Created by Yannic Rieger on 18.07.2017.
 *
 * <p>
 *
 * @author Yannic Rieger
 */
public interface TitlePacket extends Packet {

  EnumWrappers.TitleAction getAction();

  void setAction(EnumWrappers.TitleAction action);

  WrappedChatComponent getTitle();

  void setTitle(WrappedChatComponent value);

  int getFadeIn();

  void setFadeIn(int fadeIn);

  int getFadeOut();

  void setFadeOut(int fadeOut);

  int getStay();

  void setStay(int stay);
}

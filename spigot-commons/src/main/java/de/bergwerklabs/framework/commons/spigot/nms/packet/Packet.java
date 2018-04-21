package de.bergwerklabs.framework.commons.spigot.nms.packet;

import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.entity.Player;

/**
 * Created by Yannic Rieger on 15.07.2017.
 *
 * <p>
 *
 * @author Yannic Rieger
 */
public interface Packet {

  void recievePacket(Player player);

  void sendPacket(Player player);

  PacketContainer getHandle();
}

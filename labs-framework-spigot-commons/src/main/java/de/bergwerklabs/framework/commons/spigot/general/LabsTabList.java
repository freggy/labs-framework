package de.bergwerklabs.framework.commons.spigot.general;

import de.bergwerklabs.framework.commons.spigot.general.reflection.LabsReflection;
import de.bergwerklabs.framework.commons.spigot.nms.NmsUtil;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Yannic Rieger on 06.06.2017.
 * <p> Contains functionality for creating header and footer for the tab list. </p>
 *
 * @author Yannic Rieger
 */
public class LabsTabList {

    /**
     * Gets the header of the TabList.
     */
    public String[] getHeader() {
        return header.split("\n");
    }

    /**
     * Gets the footer of the TabList.
     */
    public String[] getFooter() {
        return footer.split("\n");
    }

    private String header;
    private String footer;

    /**
     * @param header Header of the tab list.
     * @param footer Footer of the tab list
     */
    public LabsTabList(String[] header, String[] footer) {
        this.header = StringUtils.join(header, "\n");
        this.footer = StringUtils.join(footer, "\n");
    }

    /**
     * Sends header and footer to the specified player.
     * @param p Player to receive header and footer.
     */
    public void send(Player p) {
        this.sendHeaderAndFooter(this.header, this.footer, p);
    }

    /**
     * Sends header and footer to every player on the server.
     */
    public void broadcast() {
        Bukkit.getServer().getOnlinePlayers().forEach(player -> this.sendHeaderAndFooter(this.header, this.footer, player));
    }

    /**
     * Sends header and footer to player.
     * @param header Header to be send.
     * @param footer Footer to be send.
     * @param player Player to receive header and footer.
     */
    private void sendHeaderAndFooter(String header, String footer, Player player) {

        try {
            Object packet = LabsReflection.getNmsClass("PacketPlayOutPlayerListHeaderFooter")
                                                  .getConstructor(LabsReflection.getNmsClass("IChatBaseComponent"))
                                                  .newInstance(NmsUtil.getIChatBaseComponent(header));

            LabsReflection.setFieldValue(LabsReflection.getField(packet.getClass(), "b"), packet, NmsUtil
                    .getIChatBaseComponent(footer));
            NmsUtil.sendPacketOverPlayerConnection(player, packet);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}

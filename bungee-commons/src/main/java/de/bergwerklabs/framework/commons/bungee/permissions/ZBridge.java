package de.bergwerklabs.framework.commons.bungee.permissions;

import net.md_5.bungee.api.ChatColor;

import java.util.UUID;

/**
 * Created by Yannic Rieger on 29.11.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class ZBridge {

    private PermissionDao dao;

    public ZBridge(String user, String password) {
        this.dao = new PermissionDao(user, password);
    }

    public String getRankName(UUID player) {
        return this.dao.getRankInfo(player).getRankName();
    }

    public ChatColor getRankColor(UUID player) {
        return dao.getRankInfo(player).getRankColor();
    }

    public RankInfo getRankInfo(UUID player) {
        return dao.getRankInfo(player);
    }
}

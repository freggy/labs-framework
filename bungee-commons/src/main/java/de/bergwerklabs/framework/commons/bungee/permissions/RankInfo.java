package de.bergwerklabs.framework.commons.bungee.permissions;

import net.md_5.bungee.api.ChatColor;

import java.util.Set;

/**
 * Created by Yannic Rieger on 29.11.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class RankInfo {

    public String getRankName() {
        return rankName;
    }

    public ChatColor getRankColor() {
        return rankColor;
    }

    public PermissionGroup getGroup() {
        return group;
    }

    private String rankName;
    private ChatColor rankColor;
    private PermissionGroup group;

    public RankInfo(String rankName, String rankColor, int groupId) {
        this.rankName = rankName;
        this.rankColor = ChatColor.getByChar(rankColor.toCharArray()[1]);
        this.group = PermissionGroup.getById(groupId);
    }
}

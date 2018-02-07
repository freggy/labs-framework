package de.bergwerklabs.framework.commons.bungee.permissions;

import de.bergwerklabs.framework.commons.bungee.BungeeCommons;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yannic Rieger on 29.11.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class ZBridge {

    private static final Map<UUID, RankInfo> RANK_INFO_CACHE = new ConcurrentHashMap<>();
    private static boolean initialized = false;
    private PermissionDao dao;

    public ZBridge(String user, String password) {
        this.dao = new PermissionDao(user, password);
        // TODO: move into init method
    }

    public void init() {
        Plugin plugin = BungeeCommons.getInstance();

        // Run only one scheduler to minimize used database connections
        // this also should increase performance a bit.
        if (!initialized) {
            RANK_INFO_CACHE.putAll(this.dao.getRankInfos());
            plugin.getProxy().getScheduler().schedule(plugin, () -> {
                System.out.println("Fetching latest ZPermissions data...");
                RANK_INFO_CACHE.clear();
                RANK_INFO_CACHE.putAll(this.dao.getRankInfos());
            }, 1, 10, TimeUnit.MINUTES);
            initialized = true;
        }
    }

    public String getRankName(UUID player) {
        return this.getRankInfoOrDefault(player).getRankName();
    }

    public ChatColor getRankColor(UUID player) {
        return this.getRankInfoOrDefault(player).getRankColor();
    }

    public RankInfo getRankInfo(UUID player) {
        return this.getRankInfoOrDefault(player);
    }

    private RankInfo getRankInfoOrDefault(UUID player) {
        RankInfo rankInfo = RANK_INFO_CACHE.get(player);
        return rankInfo == null ? new RankInfo("Spieler", "§a", 1) : rankInfo;
    }
}

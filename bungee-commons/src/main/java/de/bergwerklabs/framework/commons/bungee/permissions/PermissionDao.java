package de.bergwerklabs.framework.commons.bungee.permissions;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import de.bergwerklabs.framework.commons.database.tablebuilder.Database;
import de.bergwerklabs.framework.commons.database.tablebuilder.DatabaseType;
import de.bergwerklabs.framework.commons.database.tablebuilder.statement.Row;
import de.bergwerklabs.framework.commons.database.tablebuilder.statement.Statement;
import de.bergwerklabs.framework.commons.database.tablebuilder.statement.StatementResult;
import de.bergwerklabs.framework.commons.misc.Tuple;

import javax.xml.crypto.Data;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yannic Rieger on 29.11.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
class PermissionDao {

    private Cache<UUID, RankInfo> rankInfoCache = CacheBuilder.newBuilder()
                                                              .expireAfterWrite(5, TimeUnit.MINUTES)
                                                              .expireAfterAccess(5, TimeUnit.MINUTES)
                                                              .build();
    private Database database;

    PermissionDao(String user, String password) {
        this.database = new Database(DatabaseType.MySQL, "sql.bergwerklabs.de", "zPermissions", user, password);
    }

    RankInfo getRankInfo(UUID uuid) {
        if (!rankInfoCache.asMap().keySet().contains(uuid)) {
            int groupId = this.getGroupId(uuid);
            Tuple<String, String> displayInfo = this.getRankDisplayInfo(groupId);
            this.rankInfoCache.put(uuid, new RankInfo(displayInfo.getValue1(), displayInfo.getValue2(), groupId));
        }
        return this.rankInfoCache.getIfPresent(uuid);
    }

    private int getGroupId(UUID player) {
        Statement statement = this.database.prepareStatement("SELECT * FROM memberships WHERE member = ?");
        StatementResult result = statement.execute(player.toString().replace("-", ""));
        Row[] rows = result.getRows();
        if (rows.length < 1) return 1;
        statement.close();
        return rows[0].getLong("group_id").intValue();
    }

    private Tuple<String, String> getRankDisplayInfo(int groupId) {
        Statement statement = database.prepareStatement("SELECT * FROM metadata WHERE entity_id = ? AND (name = ? OR name = ?)");
        StatementResult result = statement.execute(groupId, "suffix", "prefix");
        Row[] rows = result.getRows();
        if (rows.length < 2) return new Tuple<>("Spieler", "§a");

        String rankName = "", rankColor = "";

        for (Row row : rows) {
            String suffixOrPrefix = row.getString("name");
            String value = row.getString("string_value");

            if (suffixOrPrefix.equalsIgnoreCase("suffix")) {
                rankName = value;
            }
            else rankColor = value;
        }
        statement.close();
        return new Tuple<>(rankName, rankColor);
    }
}

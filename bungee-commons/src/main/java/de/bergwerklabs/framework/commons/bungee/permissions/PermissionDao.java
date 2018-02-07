package de.bergwerklabs.framework.commons.bungee.permissions;

import de.bergwerklabs.framework.commons.database.tablebuilder.Database;
import de.bergwerklabs.framework.commons.database.tablebuilder.DatabaseType;
import de.bergwerklabs.framework.commons.database.tablebuilder.statement.Row;
import de.bergwerklabs.framework.commons.database.tablebuilder.statement.Statement;
import de.bergwerklabs.framework.commons.database.tablebuilder.statement.StatementResult;
import de.bergwerklabs.framework.commons.misc.Tuple;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Yannic Rieger on 29.11.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
class PermissionDao {
    private Database database;
    private Pattern shortUuidPattern = Pattern.compile("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})");


    PermissionDao(String user, String password) {
        this.database = new Database(DatabaseType.MySQL, "sql.bergwerklabs.de", "zPermissions", user, password);
    }

    RankInfo getRankInfo(UUID uuid) {
        int groupId = this.getGroupId(uuid);
        Tuple<String, String> displayInfo = this.getRankDisplayInfo(groupId);
        return new RankInfo(displayInfo.getValue1(), displayInfo.getValue2(), groupId);
    }

    private int getGroupId(UUID player) {
        Statement statement = this.database.prepareStatement("SELECT * FROM memberships WHERE member = ?");
        StatementResult result = statement.execute(player.toString().replace("-", ""));
        statement.close();
        Row[] rows = result.getRows();
        if (rows.length < 1) return 1;
        return rows[0].getLong("group_id").intValue();
    }

    public Map<UUID, RankInfo> getRankInfos() {
        Map<UUID, RankInfo> map = new HashMap<>();

        Statement statement = this.database.prepareStatement("SELECT * FROM memberships");
        StatementResult result = statement.execute();
        statement.close();
        Row[] rows = result.getRows();

        for (Row row : rows) {
            UUID uuid = this.toLongUuid(row.getString("member"));
            int id = row.getLong("group_id").intValue();
            Tuple<String, String> displayInfo = this.getRankDisplayInfo(id);
            map.put(uuid,new RankInfo(displayInfo.getValue1(), displayInfo.getValue2(), id));
        }
        return map;
    }


    private Tuple<String, String> getRankDisplayInfo(int groupId) {
        Statement statement = database.prepareStatement("SELECT * FROM metadata WHERE entity_id = ? AND (name = ? OR name = ?)");
        StatementResult result = statement.execute(groupId, "suffix", "prefix");
        statement.close();
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
        return new Tuple<>(rankName, rankColor);
    }

    public UUID toLongUuid(String shortUuid) {
        Matcher matcher = this.shortUuidPattern.matcher(shortUuid);

        if (matcher.matches()) {
            String uuid = matcher.replaceAll("$1-$2-$3-$4-$5");
            return UUID.fromString(uuid);
        }

        return null;
    }
}

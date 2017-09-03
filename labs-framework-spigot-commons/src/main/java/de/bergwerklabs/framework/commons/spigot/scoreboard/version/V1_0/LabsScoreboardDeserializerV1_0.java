package de.bergwerklabs.framework.commons.spigot.scoreboard.version.V1_0;

import com.google.gson.JsonObject;
import de.bergwerklabs.framework.commons.spigot.general.LabsController;
import de.bergwerklabs.framework.commons.spigot.general.method.UpdateMethod;
import de.bergwerklabs.framework.commons.spigot.json.JsonUtil;
import de.bergwerklabs.framework.commons.spigot.json.version.Deserializer;
import de.bergwerklabs.framework.commons.spigot.json.version.VersionedJsonReflectionUtil;
import de.bergwerklabs.framework.commons.spigot.scoreboard.LabsScoreboard;
import de.bergwerklabs.framework.commons.spigot.scoreboard.Row;
import org.bukkit.ChatColor;

/**
 * Created by Yannic Rieger on 03.05.2017.
 * <p> Deserializer for v1.0 LabsScoreboards </p>
 * @author Yannic Rieger
 */
public class LabsScoreboardDeserializerV1_0 implements Deserializer<LabsScoreboard> {

    private String blank = ChatColor.RED.toString();
    private LabsController controller;

    @Override
    public LabsScoreboard deserialize(JsonObject json) {

        if (json.has("class"))
            controller = VersionedJsonReflectionUtil.controllerFromJson(json);

        LabsScoreboard scoreboard = new LabsScoreboard(json.get("title").getAsString(), json.get("id").getAsString());

        JsonUtil.jsonArrayToJsonObjectList(json.get("rows").getAsJsonArray()).stream().forEach(jsonRow -> {
            Row row = this.createRowFromJson(jsonRow, scoreboard, controller);
            scoreboard.addRow(row.getIndex(), row);
        });

        return scoreboard;
    }

    /**
     * Creates a Row from JSON.
     * @param jsonRow JsonObject representing the row.
     * @param scoreboard Scoreboard the row belongs to.
     * @param controller Controller where the update method is contained in.
     * @return a Row created from JSON.
     */
    private Row createRowFromJson(JsonObject jsonRow, LabsScoreboard scoreboard, LabsController controller) {

        UpdateMethod<Row> updateMethod = null;
        String text = null;

        if (jsonRow.has("update"))
            updateMethod = UpdateMethod.fromJson(jsonRow.get("update"), controller);

        if (jsonRow.has("content"))
            text = jsonRow.get("content").getAsString();
        else {
            blank += ChatColor.RED.toString(); // duplicate lines will not get drawn, so add char everytime.
            text = blank;
        }

        return new Row(scoreboard, text, jsonRow.get("index").getAsInt(), updateMethod);
    }
}

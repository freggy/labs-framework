package de.bergwerklabs.framework.commons.spigot.scoreboard;

import de.bergwerklabs.framework.commons.spigot.general.Identifiable;
import de.bergwerklabs.framework.commons.spigot.json.version.Versionable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.*;

/**
 * Wrapper class for scoreboards.
 * @author Pushy, Yannic
 */
public class LabsScoreboard implements Versionable, Identifiable, Cloneable {

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public String getId() {
        return id;
    }

    /**
     * HashMap mapping from content to the row object itself.
     */
    public HashMap<String, Row> getRowsByContent() { return this.rowsByContent; }

    /**
     * Gets the org.bukkit.scoreboard.Scoreboard instance.
     */
    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    /**
     * Gets the Objective associated with this scoreboard.
     */
    public Objective getObjective() {
        return sidebar;
    }

    /**
     * Gets the display name of the scoreboard.
     */
    public String getDisplayName() { return this.displayName; }

    private List<Row> rows;
    private HashMap<String, Row> rowsByContent = new HashMap<>();
    private Scoreboard scoreboard;
    private Objective sidebar;
    private String version = "undefined";
    private String id   = "undefined";
    private String displayName;

    /**
     * @param sidebarName Title that the side bar should have.
     *                    NOTE: Title can only consist of 32 chars at max.
     * @param id Id of the scoreboard sets also the scoreboard objective name
     */
    public LabsScoreboard(String sidebarName, String id) {
        this.rows = new ArrayList<>();
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.sidebar = scoreboard.registerNewObjective(id, "dummy");
        sidebar.setDisplayName(sidebarName);
        this.sidebar.setDisplaySlot(DisplaySlot.SIDEBAR);
        this.displayName = sidebarName;
        this.id = id;
    }

    /**
     * Updates the row order.
     */
    public void updateOrder() {
        rows.forEach(row -> row.setScore(row.getScore().getScore()));
    }

    /**
     * Adds a row to the scoreboard.
     * @param index Value where the row should be placed.
     *              e.g. index = 1 would be the top row of the scoreboard
     * @param row Row to add.
     * @return Returns another scoreboard, enabling builder-like capabilities.
     */
    public LabsScoreboard addRow(int index, Row row) {
        row.setIndex(index);
        rowsByContent.put(row.getText(), row);
        rows.add(row);
        this.updateOrder();
        return this;
    }

    /**
     * Applies the scoreboard to a player.
     * @param p Player to apply the scoreboard to.
     */
    public void apply(Player p) {
        p.setScoreboard(scoreboard);
    }

    /**
     * Removes a row from the scoreboard.
     * @param row Row to remove.
     */
    public void removeRow(Row row) {
        rows.remove(row);
        scoreboard.resetScores(row.getText());
        this.updateOrder();
    }

    /**
     * Clones the current scoreboard.
     * @return a new Scoreboard, null if not cloneable.
     */
    public LabsScoreboard clone() {
        try {
            return (LabsScoreboard) super.clone();
        }
        catch (CloneNotSupportedException e) {
            Bukkit.getLogger().info("Cloning is not supported for LabsScoreboard");
            return null;
        }
    }
}

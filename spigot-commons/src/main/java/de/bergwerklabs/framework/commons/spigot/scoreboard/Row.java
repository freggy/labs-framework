package de.bergwerklabs.framework.commons.spigot.scoreboard;

import de.bergwerklabs.framework.commons.spigot.general.method.UpdateMethod;
import de.bergwerklabs.framework.commons.spigot.general.update.TaskManager;
import de.bergwerklabs.framework.commons.spigot.general.update.Updatable;
import org.bukkit.scoreboard.Score;

/**
 * This class represents a Row in a scoreboard.
 * <p> A Row can be updated via an update method.</p>
 * @author Pushy, Yannic
 */
public class Row implements Comparable<Row>, Updatable {

    /**
     * Text of the row.
     */
    public String getText() {
        return entry;
    }

    /**
     * Integer value indicating where the row is located in the scoreboard.
     * The smaller the value the lower it is located.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Gets the score.
     */
    public Score getScore() { return this.score; }

    /**
     * Sets the score.
     * @param scoreVal score value.
     */
    public void setScore(int scoreVal) {
        score.setScore(scoreVal);
    }

    /**
     * Sets the text that will be displayed.
     * When setting the text the scoreboard will be updated automatically.
     * @param con Text to display.
     */
    public void setText(String con) {

        this.scoreboard.getRowsByContent().remove(this.entry); // remove old entry
        this.scoreboard.getRowsByContent().put(con, this);

        scoreboard.getScoreboard().resetScores(entry);
        this.entry = con;
        score = this.scoreboard.getObjective().getScore(entry);
        score.setScore(this.index);
        scoreboard.updateOrder();
    }

    /**
     * Sets the index of the row
     * @param index Value indicating where the row is located.
     */
    public void setIndex(int index) {
        this.index = index;
        score.setScore(index);
    }

    private LabsScoreboard scoreboard;
    private Score score;
    private String entry;
    private int index;
    private boolean updated = false;
    private UpdateMethod<Row> updateMethod;

    /**
     * @param scoreboard Scoreboard the row belongs to.
     * @param entry Text that will be displayed.
     * @param updateMethod Method that will be invoked when update() is called.
     */
    public Row(LabsScoreboard scoreboard, String entry, int index, UpdateMethod<Row> updateMethod) {
        this.scoreboard = scoreboard;
        score = this.scoreboard.getObjective().getScore(entry);
        score.setScore(index);
        this.entry = entry;
        this.index = index;
        this.updateMethod = updateMethod;

        // TODO: work with new task manager
        //if (updateMethod != null && updateMethod.getInterval() != -1) {
            //TaskManager.registerAsyncRepeatingTask(, updateMethod.getInterval());
        //}
    }

    /**
     * @param scoreboard Scoreboard the row belongs to.
     * @param entry Text that will be displayed.
     */
    public Row(LabsScoreboard scoreboard, String entry) {
        this.scoreboard = scoreboard;
        score = this.scoreboard.getObjective().getScore(entry);
        this.entry = entry;
    }

    /**
     * Compares two Rows
     * @param o Row to compare
     * @return
     */
    @Override
    public int compareTo(Row o) {
        return o.index - this.index;
    }

    /**
     * Removes this Row.
     */
    public void remove(){
        scoreboard.removeRow(this);
    }

    @Override
    public void update() {
        try {
            Row updateRow = updateMethod.invoke();
            this.index = updateRow.getIndex();
            this.setScore(updateRow.getScore().getScore());
            this.setText(updateRow.getText());
        }
        catch (Exception e) {
            e.printStackTrace();
            this.updated = false;
        }
    }
}

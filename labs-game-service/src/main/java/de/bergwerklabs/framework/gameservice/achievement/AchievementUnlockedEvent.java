package de.bergwerklabs.framework.gameservice.achievement;

import de.bergwerklabs.framework.commons.spigot.general.LabsEvent;
import de.bergwerklabs.framework.gameservice.LabsPlayer;

/**
 * Created by Yannic Rieger on 18.05.2017.
 * <p>
 * This event is called whenever a achievement ist unlocked.
 *
 * @author Yannic Rieger
 */
public class AchievementUnlockedEvent extends LabsEvent {

    /**
     * Gets the player who unlocked the achievement.
     */
    public LabsPlayer getPlayer() {
        return player;
    }

    /**
     * Achievement that has been unlocked.
     */
    public Achievement getAchievement() {
        return achievement;
    }

    private LabsPlayer player;
    private Achievement achievement;

    /**
     * @param player      Player who unlocked the achievement.
     * @param achievement Achievement that has been unlocked.
     */
    public AchievementUnlockedEvent(LabsPlayer player, Achievement achievement) {
        this.player = player;
        this.achievement = achievement;
    }
}
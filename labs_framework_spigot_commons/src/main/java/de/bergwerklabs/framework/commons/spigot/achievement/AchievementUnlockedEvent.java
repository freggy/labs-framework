package de.bergwerklabs.framework.commons.spigot.achievement;

import de.bergwerklabs.framework.commons.spigot.game.LabsPlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Yannic Rieger on 18.05.2017.
 * <p> This event is called whenever a achievement ist unlocked. </p>
 * @author Yannic Rieger
 */
public class AchievementUnlockedEvent extends Event {

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

    @Override
    public HandlerList getHandlers() {
        return null;
    }

    private LabsPlayer player;
    private Achievement achievement;

    /**
     * @param player Player who unlocked the achievement.
     * @param achievement Achievement that has been unlocked.
     */
    public AchievementUnlockedEvent(LabsPlayer player, Achievement achievement) {
        this.player = player;
        this.achievement = achievement;
    }
}

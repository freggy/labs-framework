package de.bergwerklabs.framework.bedrock.api.achievement;

import de.bergwerklabs.framework.bedrock.api.LabsPlayer;
import org.bukkit.event.Listener;

/**
 * Created by Yannic Rieger on 18.05.2017.
 * <p>
 * Achievement that can be unlocked in a minigame.
 *
 * @author Yannic Rieger
 */
public abstract class Achievement implements Listener {

    /**
     * Gets the name of the achievement.
     */
    public String getName() { return this.name; }

    /**
     * Gets the name of the achievement.
     */
    public String[] getDescription() { return this.description; }

    private String name;
    private String[] description;
    private boolean isUnlocked = false;

    /**
     * Instructions that will be executed when the achievement is unlocked.
     *
     * @param player Player that has unlocked the achievement.
     * @param achievement Achievement that has been unlocked.
     */
    public abstract void AchievementUnlockedAction(LabsPlayer player, Achievement achievement);

    void saveState(LabsPlayer player) {

    }

    /**
     * @param name Name of the achievement
     * @param description Description of the achievement.
     */
    public Achievement(String name, String... description) {
        this.name = name;
        this.description = description;
    }
}

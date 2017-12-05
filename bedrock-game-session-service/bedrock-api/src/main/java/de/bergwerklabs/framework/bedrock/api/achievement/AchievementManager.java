package de.bergwerklabs.framework.bedrock.api.achievement;

import de.bergwerklabs.framework.bedrock.api.LabsPlayer;
import de.bergwerklabs.framework.bedrock.api.event.achievement.AchievementUnlockedEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yannic Rieger on 18.05.2017.
 * <p>
 * Provides functionality managing achievements and handling AchievementUnlocked events.
 * <p>
 * NOTE: This class follows the singleton pattern, which means that it can only be instantiated once.
 *
 * @author Yannic Rieger
 */
public class AchievementManager implements Listener {

    /**
     * Gets a unmodifiable Map containing all achievements.
     */
    public Map getAchievements() { return Collections.unmodifiableMap(achievements); }

    private HashMap<String, Achievement> achievements = new HashMap<>();
    private static AchievementManager instance;

    public AchievementManager() {
        if (instance != null) {
            Bukkit.getLogger().warning("AchievementManager can only be instantiated once.");
            return;
        }
        instance = this;
    }

    /**
     * Registers a new achievement to the manager.
     *
     * @param achievement Achievement to register.
     */
    public void registerAchievement(Achievement achievement) {
        this.achievements.put(achievement.getName(), achievement);
    }

    @EventHandler
    public void onAchievementUnlocked(AchievementUnlockedEvent e) {
        LabsPlayer player = e.getPlayer();
        // TODO: check if player already unlocked the achievement
        Achievement achievement = this.achievements.get(e.getAchievement().getName());
        achievement.saveState(player);
        achievement.AchievementUnlockedAction(player, e.getAchievement());
    }
}

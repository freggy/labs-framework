package de.bergwerklabs.framework.commons.spigot.general.update;

import de.bergwerklabs.framework.commons.spigot.SpigotCommons;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Yannic Rieger on 28.04.2017.
 * <p> Class keeps track of the current update tasks. </p>
 * @author Yannic Rieger
 */
public class TaskManager {

    private static HashMap<Integer, BukkitTask> tasks = new HashMap<>();
    private static HashMap<Integer, ArrayList<Updatable>> items = new HashMap<>();

    /**
     * Registers a new Updatable with the given interval
     * @param interval interval to update the object in.
     */
    public static void registerNewUpdatable(Integer interval, Updatable item) {
        Validate.notNull(item);
        Validate.notNull(interval);

        if (!items.containsKey(interval))
            items.put(interval, new ArrayList<>());

        items.get(interval).add(item);

        if (!tasks.containsKey(interval))
            tasks.put(interval, createUpdateScheduler(interval));
    }

    /**
     * Creates a new scheduler to update the item.
     * @param interval interval in which the update mehtod gets called.
     */
    private static BukkitTask createUpdateScheduler(Integer interval) {
        return Bukkit.getScheduler().runTaskTimerAsynchronously(SpigotCommons.getInstance(), () -> {
                items.get(interval).forEach(item -> item.update());
        }, 0, interval);
    }
}

package de.bergwerklabs.framework.commons.spigot.general.update;

import org.bukkit.Bukkit;

import io.netty.util.internal.ConcurrentSet;
import org.bukkit.plugin.Plugin;

import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author Benedikt
 */
public class TaskManager {

    private static final Set<Task> syncTasks = new ConcurrentSet<>();
    private static final Set<Task> asyncTasks = new ConcurrentSet<>();

    /**
     *
     * @param plugin
     */
    public static void startTimers(Plugin plugin) {
        Bukkit.getScheduler().runTaskTimer(plugin, createRunnable(syncTasks), 0L, 1L);
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, createRunnable(asyncTasks), 0L, 1L);
    }

    /**
     *
     * @param task
     */
    public static void stopTask(Task task) {
        syncTasks.remove(task);
        asyncTasks.remove(task);
    }

    /**
     *
     * @param task
     * @param delay
     * @param interval
     * @return
     */
    public static Task registerSyncRepeatingTask(Updatable task, long delay, long interval) {
        Task result = new Task(task, delay, interval);
        syncTasks.add(result);
        return result;
    }

    /**
     *
     * @param task
     * @param delay
     * @return
     */
    public static Task registerSyncDelayedTask(Updatable task, long delay) {
        Task result = new Task(task, delay);
        syncTasks.add(result);
        return result;
    }

    /**
     *
     * @param task
     * @param delay
     * @param interval
     * @return
     */
    public static Task registerAsyncRepeatingTask(Updatable task, long delay, long interval) {
        Task result = new Task(task, delay, interval);
        asyncTasks.add(result);
        return result;
    }

    /**
     *
     * @param task
     * @param delay
     * @return
     */
    public static Task registerAsyncDelayedTask(Updatable task, long delay) {
        Task result = new Task(task, delay);
        asyncTasks.add(result);
        return result;
    }

    /**
     *
     * @param tasks
     * @return
     */
    private static Runnable createRunnable(Set<Task> tasks) {
        AtomicLong ticks = new AtomicLong(0);
        return () -> {
            long current = ticks.getAndIncrement();

            for (Task task : tasks) {

                if (task.getCreated() < 0) {
                    task.setCreated(current);
                }

                if ((!task.isStarted() && task.getCreated() >= 0 && task.getCreated() + task.getDelay() <= current)
                        || (task.isStarted() && task.isRepeating() && task.getLastUpdate() >= 0 && task.isDone() && task.getLastUpdate() + task.getInterval() <= current)) {
                    task.setStarted(true);

                    task.update();
                    task.setLastUpdate(current);

                    if (!task.isRepeating()) {
                        tasks.remove(task);
                    }
                }
            }
        };
    }
}


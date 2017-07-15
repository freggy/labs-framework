package de.bergwerklabs.framework.commons.spigot.general.timer;

import de.bergwerklabs.framework.commons.spigot.SpigotCommons;
import de.bergwerklabs.framework.commons.spigot.general.timer.event.LabsTimerStartEvent;
import de.bergwerklabs.framework.commons.spigot.general.timer.event.LabsTimerStopEvent;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

/**
 * Created by Yannic Rieger on 07.05.2017.
 * <p> Timer which runs every second. </p>
 * @author Yannic Rieger
 */
public class LabsTimer {

    /**
     * Gets the amount of time left.
     */
    public int timeLeft() { return this.timeLeft; }

    /**
     * Gets the duration the timer will run.
     */
    public int getDuration() { return this.duration; }

    private int timeLeft, duration;
    private LabsTimerRunnable runnable;
    private BukkitTask task;

    /**
     * @param duration Duration the timer will run.
     * @param runnable Method to invoke.
     */
    public LabsTimer(int duration, LabsTimerRunnable runnable) {
        this.duration = duration;
        this.timeLeft = duration;
        this.runnable = runnable;
    }

    /**
     * Starts the timer that will run every second.
     */
    public void start() {
        this.startTask();
        Bukkit.getServer().getPluginManager().callEvent(new LabsTimerStartEvent(this, LabsTimerStartCause.INITIAL));
    }

    /**
     * Stops the timer.
     */
    public void stop() {
        task.cancel();
        Bukkit.getServer().getPluginManager().callEvent(new LabsTimerStopEvent(this));
    }

    /**
     * Resumes at the time the timer stopped.
     */
    public void resume() {
        this.startTask();
        Bukkit.getServer().getPluginManager().callEvent(new LabsTimerStartEvent(this, LabsTimerStartCause.RESUMED));
    }

    /**
     * Starts the timer task.
     */
    private void startTask() {
        task = Bukkit.getScheduler().runTaskTimerAsynchronously(SpigotCommons.getInstance(), () -> {
            runnable.run(timeLeft);
            if (timeLeft <= 0) this.stop();
            timeLeft--;
        }, 0, 20L);
    }
}

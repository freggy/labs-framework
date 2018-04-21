package de.bergwerklabs.framework.commons.spigot.general.timer;

import de.bergwerklabs.framework.commons.spigot.SpigotCommons;
import de.bergwerklabs.framework.commons.spigot.general.timer.event.LabsTimerStartEvent;
import de.bergwerklabs.framework.commons.spigot.general.timer.event.LabsTimerStopEvent;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

/**
 * Created by Yannic Rieger on 07.05.2017.
 *
 * <p>Timer which runs every second.
 *
 * @author Yannic Rieger
 */
public class LabsTimer {

  private final Set<Consumer<LabsTimerStopEvent>> LISTENERS = new HashSet<>();
  private int timeLeft, duration;
  private LabsTimerRunnable runnable;
  private BukkitTask task;
  private boolean isRunning;
  /**
   * @param duration Duration the timer will run.
   * @param runnable Method to invoke.
   */
  public LabsTimer(int duration, LabsTimerRunnable runnable) {
    this.duration = duration;
    this.timeLeft = duration;
    this.runnable = runnable;
  }

  /** Gets the amount of time left. */
  public int timeLeft() {
    return this.timeLeft;
  }

  /** Gets the duration the timer will run. */
  public int getDuration() {
    return this.duration;
  }

  public boolean isRunning() {
    return isRunning;
  }

  /**
   * Sets the time left for this timer.
   *
   * @param timeLeft time in seconds that is left.
   */
  public void setTimeLeft(int timeLeft) {
    this.timeLeft = timeLeft;
  }

  /** Starts the timer that will run every second. */
  public void start() {
    this.startTask();
    this.timeLeft = duration;
    Bukkit.getServer()
        .getPluginManager()
        .callEvent(new LabsTimerStartEvent(this, LabsTimerStartCause.INITIAL));
  }

  public void restart() {
    this.stopTask(LabsTimerStopCause.RESTART);
    this.timeLeft = this.duration;
    this.startTask();
  }

  /** Stops the timer. */
  public void stop() {
    this.stopTask(LabsTimerStopCause.STOPPED);
  }

  /** Resumes at the time the timer stopped. */
  public void resume() {
    this.startTask();
    Bukkit.getServer()
        .getPluginManager()
        .callEvent(new LabsTimerStartEvent(this, LabsTimerStartCause.RESUMED));
  }

  public void addStopListener(Consumer<LabsTimerStopEvent> eventConsumer) {
    this.LISTENERS.add(eventConsumer);
  }

  /** Starts the timer task. */
  private void startTask() {
    this.isRunning = true;
    if (this.task == null) {
      task =
          Bukkit.getScheduler()
              .runTaskTimer(
                  SpigotCommons.getInstance(),
                  () -> {
                    if (timeLeft <= 0) {
                      this.stopTask(LabsTimerStopCause.TIMES_UP);
                      return;
                    }
                    runnable.run(timeLeft);
                    timeLeft--;
                  },
                  20,
                  20);
    }
  }

  /**
   * Stops the timer with the given cause
   *
   * @param cause Reason why the timer stopped.
   */
  private void stopTask(LabsTimerStopCause cause) {
    if (!this.isRunning) return;
    if (task != null) this.task.cancel();
    this.isRunning = false;
    this.task = null;
    LabsTimerStopEvent event = new LabsTimerStopEvent(this, cause);
    this.LISTENERS.forEach(eventConsumer -> eventConsumer.accept(event));
    Bukkit.getServer().getPluginManager().callEvent(event);
  }
}

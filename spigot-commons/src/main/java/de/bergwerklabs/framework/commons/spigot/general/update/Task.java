package de.bergwerklabs.framework.commons.spigot.general.update;

/** @author Benedikt */
public class Task {

  private final Updatable updatable;
  private final long delay;
  private final long interval;
  private final boolean repeating;
  private long lastUpdate = -1;
  private long created = -1;
  private boolean done = true;
  private boolean started = false;

  /**
   * @param updatable
   * @param delay
   */
  public Task(Updatable updatable, long delay) {
    if (updatable == null) throw new IllegalArgumentException("The updatable cannot be null.");
    if (delay < 0) throw new IllegalArgumentException("The delay cannot be smaller than zero.");

    this.updatable = updatable;
    this.delay = delay;
    this.interval = 0;
    this.repeating = false;
  }

  /**
   * @param updatable
   * @param delay
   * @param interval
   */
  public Task(Updatable updatable, long delay, long interval) {
    if (updatable == null) throw new IllegalArgumentException("The updatable cannot be null.");
    if (delay < 0) throw new IllegalArgumentException("The delay cannot be smaller than zero.");
    if (interval <= 0)
      throw new IllegalArgumentException(
          "The interval cannot be smaller or equal to zero for repeating tasks.");

    this.updatable = updatable;
    this.delay = delay;
    this.interval = interval;
    this.repeating = true;
  }

  /** */
  public boolean isDone() {
    return done;
  }

  /** */
  public boolean isStarted() {
    return started;
  }

  /** @param started */
  public void setStarted(boolean started) {
    this.started = started;
  }

  /** */
  public long getDelay() {
    return delay;
  }

  /** */
  public long getInterval() {
    return interval;
  }

  /** */
  public long getCreated() {
    return created;
  }

  /** @param created */
  public void setCreated(long created) {
    this.created = created;
  }

  /** */
  public long getLastUpdate() {
    return lastUpdate;
  }

  /** @param lastUpdate */
  public void setLastUpdate(long lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

  /** */
  public boolean isRepeating() {
    return repeating;
  }

  /** */
  public void update() {
    done = false;
    updatable.update();
    done = true;
  }

  /** */
  public void cancel() {
    TaskManager.stopTask(this);
  }
}

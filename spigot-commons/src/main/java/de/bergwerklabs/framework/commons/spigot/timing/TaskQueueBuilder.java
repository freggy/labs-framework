package de.bergwerklabs.framework.commons.spigot.timing;

import de.bergwerklabs.framework.commons.misc.Triplet;
import java.util.ArrayList;
import java.util.List;

public class TaskQueueBuilder {

  private final List<Triplet<Long, Runnable, Boolean>> tasks = new ArrayList<>();

  private long delay = 0L;

  public TaskQueueBuilder(Runnable task) {
    this(task, 0L, true);
  }

  public TaskQueueBuilder(Runnable task, boolean sync) {
    this(task, 0L, sync);
  }

  public TaskQueueBuilder(Runnable task, long delay) {
    addTask(task, delay, true);
  }

  public TaskQueueBuilder(Runnable task, long delay, boolean sync) {
    addTask(task, delay, sync);
  }

  private void addTask(Runnable task, long delay, boolean sync) {
    this.delay = 0L;
    tasks.add(new Triplet<>(delay, task, sync));
  }

  public TaskQueueBuilder waitTicks(long ticks) {
    delay = ticks;
    return this;
  }

  public TaskQueueBuilder waitMilliseconds(long ms) {
    return waitTicks(Math.round(ms / 50.0));
  }

  public TaskQueueBuilder then(Runnable task) {
    addTask(task, delay, true);
    return this;
  }

  public TaskQueueBuilder then(Runnable task, boolean sync) {
    addTask(task, delay, sync);
    return this;
  }

  public TaskQueue create() {
    return new TaskQueue(tasks);
  }
}

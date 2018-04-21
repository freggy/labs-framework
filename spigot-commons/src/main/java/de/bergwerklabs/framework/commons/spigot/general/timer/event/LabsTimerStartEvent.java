package de.bergwerklabs.framework.commons.spigot.general.timer.event;

import de.bergwerklabs.framework.commons.spigot.general.timer.LabsTimer;
import de.bergwerklabs.framework.commons.spigot.general.timer.LabsTimerStartCause;

/**
 * Created by Yannic Rieger on 07.05.2017.
 *
 * <p>Event which will fire if a timer starts.
 *
 * @author Yannic Rieger
 */
public class LabsTimerStartEvent extends LabsTimerEventBase {

  private LabsTimerStartCause cause;

  /**
   * @param timer {@link LabsTimer} that has been started.
   * @param cause The way the timer got started.
   */
  public LabsTimerStartEvent(LabsTimer timer, LabsTimerStartCause cause) {
    super(timer);
    this.cause = cause;
  }

  /** Gets the how the timer has been started. */
  public LabsTimerStartCause getCause() {
    return cause;
  }
}

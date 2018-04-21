package de.bergwerklabs.framework.commons.spigot.general.timer.event;

import de.bergwerklabs.framework.commons.spigot.general.LabsEvent;
import de.bergwerklabs.framework.commons.spigot.general.timer.LabsTimer;

/**
 * Created by Yannic Rieger on 07.05.2017.
 *
 * <p>Base class for LabsTimer events.
 *
 * @author Yannic Rieger
 */
class LabsTimerEventBase extends LabsEvent {

  protected LabsTimer timer;

  public LabsTimerEventBase(LabsTimer timer) {
    this.timer = timer;
  }

  /** */
  public LabsTimer getTimer() {
    return this.timer;
  }
}

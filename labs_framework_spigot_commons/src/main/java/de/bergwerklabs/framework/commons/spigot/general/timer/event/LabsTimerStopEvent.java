package de.bergwerklabs.framework.commons.spigot.general.timer.event;

import de.bergwerklabs.framework.commons.spigot.general.timer.LabsTimer;
import de.bergwerklabs.framework.commons.spigot.general.timer.LabsTimerStopCause;

/**
 * Created by Yannic Rieger on 07.05.2017.
 * <p> Event which will fire when a timer stops.
 *
 * @author Yannic Rieger
 */
public class LabsTimerStopEvent extends LabsTimerEventBase {

    /**
     * Gets the cause as to why the timer stopped.
     */
    public LabsTimerStopCause getCause() {
        return cause;
    }

    private LabsTimerStopCause cause;

    /**
     * @param timer {@link LabsTimer} which stopped.
     * @param cause Reason why the {@link LabsTimer} stopped.
     */
    public LabsTimerStopEvent(LabsTimer timer, LabsTimerStopCause cause) {
        super(timer);
        this.cause = cause;
    }
}

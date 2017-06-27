package de.bergwerklabs.framework.commons.spigot.general.timer.event;

import de.bergwerklabs.framework.commons.spigot.general.timer.LabsTimer;

/**
 * Created by Yannic Rieger on 07.05.2017.
 * <p> Event which will fire when a timer stops. </p>
 * @author Yannic Rieger
 */
public class LabsTimerStopEvent extends LabsTimerEventBase {

    /**
     *
     * @param timer
     */
    public LabsTimerStopEvent(LabsTimer timer) {
        super(timer);
    }
}

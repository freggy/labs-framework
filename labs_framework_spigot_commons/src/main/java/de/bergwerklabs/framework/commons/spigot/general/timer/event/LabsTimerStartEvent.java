package de.bergwerklabs.framework.commons.spigot.general.timer.event;

import de.bergwerklabs.framework.commons.spigot.general.timer.LabsTimer;

/**
 * Created by Yannic Rieger on 07.05.2017.
 * <p> Event which will fire if a timer starts. </p>
 * @author Yannic Rieger
 */
public class LabsTimerStartEvent extends LabsTimerEventBase {

    /**
     *
     * @param timer
     */
    public LabsTimerStartEvent(LabsTimer timer) {
        super(timer);
    }
}

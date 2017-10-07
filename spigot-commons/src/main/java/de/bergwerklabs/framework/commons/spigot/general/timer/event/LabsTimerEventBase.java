package de.bergwerklabs.framework.commons.spigot.general.timer.event;

import de.bergwerklabs.framework.commons.spigot.general.LabsEvent;
import de.bergwerklabs.framework.commons.spigot.general.timer.LabsTimer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Yannic Rieger on 07.05.2017.
 * <p> Base class for LabsTimer events. </p>
 * @author Yannic Rieger
 */
class LabsTimerEventBase extends LabsEvent {

    /**
     *
     */
    public LabsTimer getTimer() { return this.timer; }

    protected LabsTimer timer;

    public LabsTimerEventBase(LabsTimer timer) {
        this.timer = timer;
    }

}

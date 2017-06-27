package de.bergwerklabs.framework.commons.spigot.general.timer.event;

import de.bergwerklabs.framework.commons.spigot.general.timer.LabsTimer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Yannic Rieger on 07.05.2017.
 * <p> Base class for LabsTimer events. </p>
 * @author Yannic Rieger
 */
class LabsTimerEventBase extends Event {

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    /**
     *
     */
    public LabsTimer getTimer() { return this.timer; }

    private static final HandlerList handlers = new HandlerList();
    protected LabsTimer timer;

    public LabsTimerEventBase(LabsTimer timer) {
        this.timer = timer;
    }

}

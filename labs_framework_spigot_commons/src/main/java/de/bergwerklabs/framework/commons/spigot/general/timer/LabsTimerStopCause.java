package de.bergwerklabs.framework.commons.spigot.general.timer;

/**
 * Created by Yannic Rieger on 15.07.2017.
 * <p> Contains a list of reasons why a {@link LabsTimer} stopped.
 *     Those will be passed to the {@link de.bergwerklabs.framework.commons.spigot.general.timer.event.LabsTimerStopEvent}.
 *
 * @author Yannic Rieger
 */
public enum LabsTimerStopCause {
    TIMES_UP, STOPPED
}

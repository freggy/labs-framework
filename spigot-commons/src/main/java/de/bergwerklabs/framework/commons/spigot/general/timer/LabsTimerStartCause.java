package de.bergwerklabs.framework.commons.spigot.general.timer;

/**
 * Created by Yannic Rieger on 15.07.2017.
 *
 * <p>Contains a list of ways a {@link LabsTimer} can be started. Those will be passed to the {@link
 * de.bergwerklabs.framework.commons.spigot.general.timer.event.LabsTimerStartEvent}.
 *
 * @author Yannic Rieger
 */
public enum LabsTimerStartCause {
  INITIAL,
  RESUMED
}

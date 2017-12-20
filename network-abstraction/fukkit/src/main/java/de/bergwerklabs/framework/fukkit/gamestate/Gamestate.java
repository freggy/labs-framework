package de.bergwerklabs.framework.fukkit.gamestate;

import java.io.Serializable;

/**
 * Created by Yannic Rieger on 20.12.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public enum Gamestate implements Serializable {
    RUNNING,
    WAITING,
    PREPARING,
    FAILED,
    RUNNING_DEATHMATCH,
    CLEANUP,
    FINISHED
}

package de.bergwerklabs.framework.fukkit;

import de.bergwerklabs.framework.fukkit.gamestate.GamestateManager;
import de.bergwerklabs.framework.nabs.PlayerdataFactory;

/**
 * Created by Yannic Rieger on 20.12.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public interface FukkitClient {

    GamestateManager getGamestateManager();

    PlayerdataFactory getDatasetFactory();

}

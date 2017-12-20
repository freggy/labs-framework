package de.bergwerklabs.framework.nabs;

import java.util.UUID;

/**
 * Created by Yannic Rieger on 20.12.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public interface PlayerdataFactory {

    PlayerdataSet createInstance(UUID uuid);

}

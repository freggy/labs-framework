package de.bergwerklabs.framework.nabs;

import java.util.UUID;

/**
 * Created by Yannic Rieger on 20.12.2017.
 * <p>
 * Creates an {@link PlayerdataSet}. Usage:
 *
 * <pre>
 *     PlayerdataFactory factory = new FactoryImp();
 *     UUID someUuid = UUID.randomUUID();
 *     PlayerdataSet set = factory.createInstance(someUuid);
 * </pre>
 *
 * @author Yannic Rieger
 */
public interface PlayerdataFactory {

    PlayerdataSet createInstance(UUID uuid);

}

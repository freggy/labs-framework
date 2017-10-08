package de.bergwerklabs.framework.commons.spigot.general;

/**
 * Created by Yannic Rieger on 02.05.2017.
 * <p> Should be implemented by every class that needs an id. </p>
 * @author Yannic Rieger
 */
public interface Identifiable {

    /**
     * Gets the id of the object.
     */
    String getId();
}

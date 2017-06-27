package de.bergwerklabs.framework.commons.spigot.general.update;

/**
 * Created by Yannic Rieger on 28.04.2017.
 * <p> Interface should be implemented by classes which need update functionality. </p>
 * @author Yannic Rieger
 */
public interface Updatable {

    /**
     * Method used for updating the object.
     */
    void update();

    /**
     * Returns a value that indicates whether or not the object has been updated.
     */
    boolean isUpdated();

}

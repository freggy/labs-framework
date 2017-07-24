package de.bergwerklabs.framework.commons.spigot.entity;

import de.bergwerklabs.framework.commons.spigot.general.reflection.LabsReflection;

import java.lang.reflect.Field;

/**
 * Created by Yannic Rieger on 24.07.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class EntityUtil {

    private static final Object lock = new Object();

    private static Field entityCount;

    /**
     *
     * @return
     */
    public static Integer getNmsId()
    {
        int entityID;
        synchronized (lock)
        {
            if (entityCount == null)
            {
                entityCount = LabsReflection.getField(LabsReflection.getNmsClass("Entity"), "entityCount");
            }

            try {
                entityID = (int) entityCount.get(null);
                entityCount.set(null, entityID + 1);
                return entityID;
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}

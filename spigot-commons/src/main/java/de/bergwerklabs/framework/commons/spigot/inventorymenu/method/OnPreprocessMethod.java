package de.bergwerklabs.framework.commons.spigot.inventorymenu.method;

import com.google.gson.JsonObject;
import de.bergwerklabs.framework.commons.spigot.general.LabsController;
import de.bergwerklabs.framework.commons.spigot.inventorymenu.event.InventoryMenuPreprocessEvent;

import java.lang.reflect.Method;

/**
 * Created by Yannic Rieger on 08.01.2018.
 * <p>
 *
 * @author Yannic Rieger
 */
public class OnPreprocessMethod {

    private Method method;

    public OnPreprocessMethod(String name, LabsController controller) {
        try {
            this.method = controller.getClass().getMethod(name, InventoryMenuPreprocessEvent.class);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void invoke(InventoryMenuPreprocessEvent event) {
        try {
            this.method.invoke(this, event);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static OnPreprocessMethod fromJson(JsonObject object, LabsController controller) {
        String name = object.get("method").getAsString();
        return new OnPreprocessMethod(name, controller);
    }
}

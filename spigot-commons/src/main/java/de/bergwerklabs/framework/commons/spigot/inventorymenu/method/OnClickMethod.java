package de.bergwerklabs.framework.commons.spigot.inventorymenu.method;

import com.google.gson.JsonObject;
import de.bergwerklabs.framework.commons.spigot.general.LabsController;
import de.bergwerklabs.framework.commons.spigot.inventorymenu.event.InventoryItemClickEvent;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yannic Rieger on 08.01.2018.
 * <p>
 *
 * @author Yannic Rieger
 */
public class OnClickMethod {

    public Set<String> getParams() {
        return params;
    }

    private Method method;
    private Set<String> params;

    public OnClickMethod(String name, LabsController controller, Set<String> params) {
        try {
            this.params = params;
            this.method = controller.getClass().getMethod(name, InventoryItemClickEvent.class);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void invoke(InventoryItemClickEvent event) {
        try {
            this.method.invoke(this, event);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static OnClickMethod fromJson(JsonObject object, LabsController controller) {
        String name = object.get("method").getAsString();
        Set<String> params = new HashSet<>();
        object.get("params").getAsJsonArray().forEach(param -> params.add(param.getAsString()));
        return new OnClickMethod(name, controller, params);
    }
}

package de.bergwerklabs.framework.commons.spigot.inventorymenu.method;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import de.bergwerklabs.framework.commons.spigot.general.LabsController;
import de.bergwerklabs.framework.commons.spigot.json.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yannic Rieger on 13.04.2017.
 * <p> Method that gets called in a specific interval. </p>
 * @author Yannic Rieger
 */
public class UpdateMethod<T>  {

    /**
     * Gets the interval in which the methods gets invoked.
     */
    public int getInterval() {
        return interval;
    }

    private int interval = -1;

    /**
     * @param method Method to invoke.
     * @param controller Controller the method is contained in.
     * @param interval Interval in which the method gets called.
     */
    public UpdateMethod(String method, LabsController controller, int interval) {
        this.interval = interval;
    }

    /**
     * @param method Method to invoke.
     * @param controller Controller the method is contained in.
     * @param interval Interval in which the method gets called.
     * @param params Parameters to be passed.
     */
    public UpdateMethod(String method, LabsController controller, int interval, List<Object> params) {
        this.interval = interval;
    }

    /**
     * Creates a UpdateMethod from JSON.
     * @param json JsonObject representing the UpdateMethod.
     * @param controller Class the method is contained in.
     * @return UpdateMethod created from JSON.
     */
    public static UpdateMethod fromJson(JsonElement json, LabsController controller) {
        if (json == null) return null;

        List<String> params = null;
        int interval = -1;

        if (json.isJsonObject()) {
            JsonObject object = json.getAsJsonObject();

            if (object.has("parameters"))
                params = JsonUtil.jsonArrayToStringList(object.get("parameters").getAsJsonArray());

            if (object.has("interval"))
                interval = object.get("interval").getAsInt();

            return new UpdateMethod(object.get("method").getAsString(), controller, interval, new ArrayList<>(params));
        }
        else return new UpdateMethod(json.getAsString(), controller, interval);
    }

}


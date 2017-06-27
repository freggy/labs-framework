package de.bergwerklabs.framework.commons.spigot.general.method;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import de.bergwerklabs.framework.commons.spigot.general.LabsController;
import de.bergwerklabs.framework.commons.spigot.json.JsonUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yannic Rieger on 13.04.2017.
 * <p> This is a wrapper class for methods you can access via reflection. </p>
 * <p> The class should make it easier dealing with invoking methods passing parameters ect. </p>
 * @author Yannic Rieger
 */
public class LabsMethod<T> {

    /**
     * Gets the Method object.
     */
    public Method getMethod() {
        return method;
    }

    /**
     *
     * @return
     */
    public List<Object> getParameters() { return this.parameters; }

    protected Method method;
    protected LabsController controller;
    protected List<Object> parameters;

    /**
     * @param method Method to invoke.
     * @param controller Controller the method is contained in.

     */
    public LabsMethod(String method, LabsController controller) {
        this.assignMethodFromController(method, controller);
        this.controller = controller;
    }

    /**
     * @param method Method to invoke.
     * @param controller Controller the method is contained in.
     * @param parameters Paramters to be passed.
     */
    public LabsMethod(String method, LabsController controller, List<Object> parameters) {

        this.assignMethodFromController(method, controller);
        this.parameters = parameters;
        this.controller = controller;
    }


    /**
     * Gets the specified method from the controller.
     * @param method Method to get.
     * @param controller Controller the method is contained in.
     */
    public void assignMethodFromController(String method, LabsController controller) {
        try {
            this.method = controller.getClass().getMethod(method, List.class);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Invokes the method.
     * @param params Parameters to be passed.
     * @param additional Additional parameters to be passed.
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public T invoke(List<Object> params, Object... additional) throws InvocationTargetException, IllegalAccessException {
        return this.invokeProtected(params, additional);
    }

    /**
     * Invokes the method.
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public T invoke() throws InvocationTargetException, IllegalAccessException {
        return this.invokeProtected(null, this.parameters);
    }

    /**
     * Creates a LabsMethod Object form JSON.
     * @param json JsonObject representing LabsMethod.
     * @param controller Controller where the method is contained in.
     * @return LabsMethod object created from JSON.
     */
    public static LabsMethod fromJson(JsonElement json, LabsController controller) {
        if (json == null) return null;

        if (json.isJsonObject()) {
            JsonObject object = json.getAsJsonObject();
            return new LabsMethod<>(object.get("method").getAsString(), controller, new ArrayList<>(JsonUtil.jsonArrayToStringList(object.get("parameters").getAsJsonArray())));
        }
        else {
            // If parameters are not specified use the default parameters (slot, item).
            // This enables writing the method declaration in one line.
            return new LabsMethod<>(json.getAsString(), controller);
        }
    }

    /**
     * Invokes the method.
     * @param params Parameters to be passed.
     * @return specified type
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    protected T invokeProtected(List<Object> params, Object... additional) throws InvocationTargetException, IllegalAccessException {
        if (params == null && additional == null)  { // Noting has been pass as arguments
            return (T)this.method.invoke(this.controller);
        }
        else {
            if (additional != null && params != null)
                params.addAll(Arrays.asList(additional));
            else return (T)this.method.invoke(this.controller, Arrays.asList(additional));

            return (T)this.method.invoke(this.controller, params);
        }
    }
}

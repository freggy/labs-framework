package de.bergwerklabs.framework.bedrock.gameserver.logging;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yannic Rieger on 24.11.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class ActionLogger {

    private Set<Action> actions = new HashSet<>();

    public void log(Action action) {
        this.actions.add(action);
    }

    public JsonElement exportLog() {
        JsonArray jsonElements = new JsonArray();
        this.actions.forEach(action -> jsonElements.add(action.toJson()));
        return jsonElements;
    }
}

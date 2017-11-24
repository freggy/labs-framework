package de.bergwerklabs.framework.bedrock.gameserver.history;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import de.bergwerklabs.framework.bedrock.api.history.Action;
import de.bergwerklabs.framework.bedrock.api.history.RoundHistoryLogger;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yannic Rieger on 24.11.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class ActionLogger implements RoundHistoryLogger {

    private Set<Action> actions = new HashSet<>();

    public void log(Action action) {
        this.actions.add(action);
    }

    public JsonElement exportHistory() {
        JsonArray jsonElements = new JsonArray();
        this.actions.forEach(action -> jsonElements.add(action.toJson()));
        return jsonElements;
    }
}

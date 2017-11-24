package de.bergwerklabs.framework.bedrock.api.history;

import com.google.gson.JsonObject;

/**
 * Created by Yannic Rieger on 24.11.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public abstract class Action {

    public ActionType getType() {
        return type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    protected ActionType type;
    protected long timestamp;

    Action(ActionType type, long timestamp) {
        this.type = type;
        this.timestamp = timestamp;
    }

    public abstract JsonObject toJson();
}

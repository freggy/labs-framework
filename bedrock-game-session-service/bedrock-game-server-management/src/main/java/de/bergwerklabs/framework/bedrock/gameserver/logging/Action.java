package de.bergwerklabs.framework.bedrock.gameserver.logging;

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

    protected ActionType type;

    Action(ActionType type) {
        this.type = type;
    }

    public abstract JsonObject toJson();
}

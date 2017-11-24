package de.bergwerklabs.framework.bedrock.gameserver.logging;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.bukkit.entity.Player;

/**
 * Created by Yannic Rieger on 24.11.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class DeathAction extends Action {

    private Player died;

    public DeathAction(ActionType type, long time, Player died) {
        super(type, time);
        this.died = died;
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("type", new JsonPrimitive(this.type.name()));
        jsonObject.add("timestamp", new JsonPrimitive(this.timestamp));
        jsonObject.add("died", JsonUtil.playerToJson(this.died));
        return jsonObject;
    }
}

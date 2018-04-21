package de.bergwerklabs.framework.bedrock.gameserver.history;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import de.bergwerklabs.framework.bedrock.api.history.Action;
import de.bergwerklabs.framework.bedrock.api.history.ActionType;
import org.bukkit.entity.Player;

/**
 * Created by Yannic Rieger on 24.11.2017.
 *
 * <p>
 *
 * @author Yannic Rieger
 */
public class KillAction extends Action {

  private Player killer, killed;

  public KillAction(ActionType type, long time, Player killer, Player killed) {
    super(type, time);
    this.killer = killer;
    this.killed = killed;
  }

  @Override
  public JsonObject toJson() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("type", new JsonPrimitive(this.type.name()));
    jsonObject.add("timestamp", new JsonPrimitive(this.timestamp));
    jsonObject.add("killer", JsonUtil.playerToJson(this.killer));
    jsonObject.add("killed", JsonUtil.playerToJson(this.killed));
    return jsonObject;
  }
}

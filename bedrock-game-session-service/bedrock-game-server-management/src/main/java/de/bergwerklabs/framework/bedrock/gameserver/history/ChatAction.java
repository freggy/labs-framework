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
public class ChatAction extends Action {

  private String message;
  private Player player;

  public ChatAction(ActionType type, long time, Player player, String message) {
    super(type, time);
    this.message = message;
    this.player = player;
  }

  @Override
  public JsonObject toJson() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("type", new JsonPrimitive(this.type.name()));
    jsonObject.add("timestamp", new JsonPrimitive(this.timestamp));
    jsonObject.add("message", new JsonPrimitive(message));
    jsonObject.add("player", JsonUtil.playerToJson(this.player));
    return jsonObject;
  }
}

package de.bergwerklabs.framework.bedrock.api.history;

import com.google.gson.JsonObject;

/**
 * Created by Yannic Rieger on 24.11.2017.
 *
 * <p>
 *
 * @author Yannic Rieger
 */
public abstract class Action {

  protected ActionType type;
  protected long timestamp;

  public Action(ActionType type, long timestamp) {
    this.type = type;
    this.timestamp = timestamp;
  }

  public ActionType getType() {
    return type;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public abstract JsonObject toJson();
}

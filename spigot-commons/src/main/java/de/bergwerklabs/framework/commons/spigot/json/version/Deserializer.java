package de.bergwerklabs.framework.commons.spigot.json.version;

import com.google.gson.JsonObject;

/**
 * Created by Yannic Rieger on 12.04.2017.
 *
 * <p>Interface which has to be implemented by json deserializers
 *
 * @author Yannic Rieger
 */
public interface Deserializer<T> extends Coder {

  /**
   * Deserializes an type T.
   *
   * @param json JsonObject to deserialize from.
   */
  T deserialize(JsonObject json);
}

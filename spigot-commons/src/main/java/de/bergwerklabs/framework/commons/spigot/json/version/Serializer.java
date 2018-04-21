package de.bergwerklabs.framework.commons.spigot.json.version;

import com.google.gson.JsonElement;

/**
 * Created by Yannic Rieger on 12.04.2017.
 *
 * <p>Interface which has to be implemented by json serializers
 *
 * @author Yannic Rieger
 */
public interface Serializer<T> extends Coder {

  /**
   * Serializes an InventoryMenu
   *
   * @param type type to serialize.
   */
  JsonElement serialize(T type);
}

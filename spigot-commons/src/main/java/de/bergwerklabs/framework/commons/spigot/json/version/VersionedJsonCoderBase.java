package de.bergwerklabs.framework.commons.spigot.json.version;

/**
 * Created by Yannic Rieger on 28.04.2017.
 *
 * <p>Base class for JSON deserializers and serializers.
 *
 * @author Yannic Rieger
 */
abstract class VersionedJsonCoderBase {

  protected String pathTovDeserializer;

  /**
   * @param pathTovDeserializer Path where the deserializer is located. For conventions see the
   *     documentation.
   */
  VersionedJsonCoderBase(String pathTovDeserializer) {
    this.pathTovDeserializer = pathTovDeserializer;
  }
}

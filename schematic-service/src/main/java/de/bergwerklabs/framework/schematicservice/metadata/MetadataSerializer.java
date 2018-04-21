package de.bergwerklabs.framework.schematicservice.metadata;

import com.flowpowered.nbt.CompoundTag;

/**
 * Created by Yannic Rieger on 29.06.2017.
 *
 * <p>
 *
 * @author Yannic Rieger
 */
public interface MetadataSerializer<T> {

  /**
   * @param metadata
   * @return
   */
  CompoundTag serialize(T metadata);
}

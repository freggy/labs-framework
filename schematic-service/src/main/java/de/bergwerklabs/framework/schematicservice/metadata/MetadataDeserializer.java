package de.bergwerklabs.framework.schematicservice.metadata;

import com.flowpowered.nbt.CompoundTag;

/**
 * Created by Yannic Rieger on 29.06.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public interface MetadataDeserializer<T> {

    /**
     *
     * @param schematicTag
     * @return
     */
    T deserialize(CompoundTag schematicTag);
}

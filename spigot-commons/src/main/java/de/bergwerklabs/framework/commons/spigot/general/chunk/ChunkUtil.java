package de.bergwerklabs.framework.commons.spigot.general.chunk;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.bukkit.Chunk;
import org.bukkit.World;

/**
 * Created by Yannic Rieger on 11.07.2017.
 *
 * <p>Provides useful utilities when working with {@link Chunk}s.
 *
 * @author Yannic Rieger
 */
public class ChunkUtil {

  /**
   * Gets the {@link Chunk} based on the given {@link ChunkFacing}.
   *
   * @param world {@link World} where the {@link Chunk} is located.
   * @param current Initial {@link Chunk}
   * @param facing Value which determines the direction the returned {@link Chunk} is located.
   * @return a {@link Chunk} based on the {@link ChunkFacing}
   */
  public static Chunk getChunk(World world, Chunk current, ChunkFacing facing) {
    return world.getChunkAt(
        (current.getX() >> 4) + facing.getXShift(), (current.getZ() >> 4) + facing.getZShift());
  }

  /**
   * Gets the Neighbours of this given {@link Chunk}.
   *
   * @param world {@link World} where the {@link Chunk} is located.
   * @param current Initial {@link Chunk}
   * @return a {@link List} of {@link Chunk}s
   */
  public static List<Chunk> getNeighbourChunks(World world, Chunk current) {
    return Arrays.stream(ChunkFacing.values())
        .map(facing -> getChunk(world, current, facing))
        .collect(Collectors.toList());
  }
}

package de.bergwerklabs.framework.commons.spigot.block.highlighter;

import org.bukkit.util.Vector;

/**
 * Created by Yannic Rieger on 02.07.2017.
 * <p> Renderer which is used to provide highlighting functionality.
 *
 * @author Yannic Rieger
 */
public abstract class BlockHighlightRenderer {

     protected Vector minPoint, maxPoint;

    /**
     * @param minPoint Minimum point of the block at this location.
     * @param maxPoint Maximum point of the block at this location.
     */
    public BlockHighlightRenderer(Vector minPoint, Vector maxPoint) {
        this.minPoint = minPoint;
        this.maxPoint = maxPoint;
    }

    /**
     * Highlights a block.
     *
     * @param facing {@link Facing} where to highlight the block.
     */
    public abstract void highlight(Facing facing);

}

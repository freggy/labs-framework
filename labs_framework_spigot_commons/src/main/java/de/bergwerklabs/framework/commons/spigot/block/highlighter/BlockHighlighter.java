package de.bergwerklabs.framework.commons.spigot.block.highlighter;

import org.bukkit.Location;
import org.bukkit.util.Vector;

/**
 * Created by Yannic Rieger on 02.07.2017.
 * <p> Class used for highlighting blocks at a given {@link Location}.
 *     Example usage:
 *
 * <pre>
 *     <code>
 *         BlockHighlighter highlighter = new BlockHighlighter(playerLocation);
 *         highlighter.highlight(Facing.COMPLETE);
 *     </code>
 * </pre>
 *
 * @author Yannic Rieger
 */
public class BlockHighlighter {

    /**
     * Gets the {@link BlockHighlightRenderer} for this {@code BlockHighlighter}.
     */
    public BlockHighlightRenderer getRenderer() {
        return renderer;
    }

    /**
     * Gets the {@link Location}.
     */
    public Location getLocation() {
        return location;
    }

    private Location location;
    private BlockHighlightRenderer renderer;

    /**
     * @param location {@link Location} where the block is located.
     */
    public BlockHighlighter(Location location) {
        this.location = location;
        this.renderer = new DefaultRenderer(new Vector(this.location.getBlockX(),
                                                       this.location.getBlockY(),
                                                       this.location.getBlockZ()),
                                            new Vector(this.location.getBlockX() + 1,
                                                       this.location.getBlockY() + 1,
                                                       this.location.getBlockZ() + 1));
    }

    /**
     * @param location {@link Location} where the block is located.
     * @param renderer {@link BlockHighlightRenderer} to render the highlighting.
     */
    public BlockHighlighter(Location location, BlockHighlightRenderer renderer) {
        this.location = location;
        this.renderer = renderer;

    }

    /**
     * Highlights the block using the render implementation of the {@link BlockHighlightRenderer},
     * if no custom renderer is given, the default one will be used instead.
     * If {@link Facing} is {@code null} the complete block will be highlighted.
     *
     * @param facing Defining which side should be highlighted.
     */
    public void highlight(Facing facing) {
        this.renderer.highlight(facing);
    }

}

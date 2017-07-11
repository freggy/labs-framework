package de.bergwerklabs.framework.commons.spigot.general.chunk;

/**
 * Created by Yannic Rieger on 11.07.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public enum ChunkFacing {

    SOUTH(0, 1),
    NORTH(0, -1),
    EAST(1, 0),
    WEST(-1, 0);

    /**
     *
     */
    public int getZShift() {
        return zShift;
    }

    /**
     *
     */
    public int getXShift() {
        return xShift;
    }

    private int xShift, zShift;

    /**
     * @param xShift
     * @param zShift
     */
    ChunkFacing(int xShift, int zShift) {
        this.xShift = xShift;
        this.zShift = zShift;
    }
}

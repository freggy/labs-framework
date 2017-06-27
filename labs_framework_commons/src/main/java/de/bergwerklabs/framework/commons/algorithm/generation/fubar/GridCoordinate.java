package de.bergwerklabs.framework.commons.algorithm.generation.fubar;
/**
 * Created by Benedikt on 19.06.2017.
 */
public class GridCoordinate {

    private final int row;
    private final int col;
    private final int gridSize;

    public GridCoordinate(int row, int col, int gridSize) {
        this.row = row;
        this.col = col;
        this.gridSize = gridSize;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getGridSize() {
        return gridSize;
    }

    public static int toIndex(int row, int col, int boardSize) {
        return row * boardSize + col;
    }
}

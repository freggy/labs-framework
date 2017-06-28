package de.bergwerklabs.framework.commons.algorithm.generation.fubar;

import de.bergwerklabs.framework.commons.algorithm.pathfinding.astar.Node;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Benedikt on 19.06.2017.
 */
public class GridNode extends Node<GridCoordinate> {

    private Set<Direction> directions = new HashSet<>();

    public GridNode(int row, int col, int gridSize) {
        super(new GridCoordinate(row, col, gridSize));
    }

    @Override
    public double getHeuristic(Node<GridCoordinate> other) {
        return Math.abs(getAssociated().getRow() - other.getAssociated().getRow())
                + Math.abs(getAssociated().getCol() - other.getAssociated().getCol());
    }

    /**
     * Adds a direction to be included for <code>getTileType()</code>.
     * @param direction the direction to be added
     */
    public void addDirection(Direction direction) {
        directions.add(direction);
    }

    public boolean hasDirection(Direction direction) {
        return directions.contains(direction);
    }

    public Set<Direction> getDirections() {
        return directions;
    }

    /**
     * @return a TileType representing all given directions
     */
    public TileType getTileType() {
        return TileType.fromDirections(directions);
    }

    @Override
    public Set<Node<GridCoordinate>> getSuccessors(Node<GridCoordinate>[] nodes) {
        Set<Node<GridCoordinate>> successors = new HashSet<>();
        successors.add(findNextNode(nodes, Direction.UP));
        successors.add(findNextNode(nodes, Direction.DOWN));
        successors.add(findNextNode(nodes, Direction.LEFT));
        successors.add(findNextNode(nodes, Direction.RIGHT));
        return successors;
    }

    /**
     * Finds the node next to this one in the given direction
     * @param nodes all nodes important for this path finding cycle
     * @param direction direction relative to this node
     * @return the node in the given direction. <code>null</code> if none was found.
     */
    public Node<GridCoordinate> findNextNode(Node<GridCoordinate>[] nodes, Direction direction) {
        int row = getAssociated().getRow();
        int col = getAssociated().getCol();
        int size = getAssociated().getGridSize();

        if (direction == Direction.UP) {
            return findNode(nodes, row - 1, col, size);
        } else if (direction == Direction.DOWN) {
            return findNode(nodes, row + 1, col, size);
        } else if (direction == Direction.LEFT) {
            return findNode(nodes, row, col - 1, size);
        } else if (direction == Direction.RIGHT) {
            return findNode(nodes, row, col + 1, size);
        }

        return null;
    }

    /**
     * Finds a node for the given coordinates
     * @param nodes all nodes important for this path finding cycle
     * @param row y coordinate
     * @param col x coordinate
     * @param gridSize size of both sides
     * @return the node at the given position. <code>null</code> if none was found or the coordinates are out of board.
     */
    public static Node<GridCoordinate> findNode(Node<GridCoordinate>[] nodes, int row, int col, int gridSize) {
        if (row < 0 || col < 0 || row >= gridSize || col >= gridSize) return null;
        return nodes[GridCoordinate.toIndex(row, col, gridSize)];
    }

    @Override
    public String toString() {
        return getAssociated().getRow() + "|" + getAssociated().getCol();
    }
}

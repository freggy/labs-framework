package de.bergwerklabs.framework.commons.algorithm.generation.fubar;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Benedikt on 19.06.2017.
 */
public enum TileType {
    END_LEFT(Direction.LEFT),
    END_RIGHT(Direction.RIGHT),
    END_UP(Direction.UP),
    END_DOWN(Direction.DOWN),
    STRAIGHT_VERTICAL(Direction.UP, Direction.DOWN),
    STRAIGHT_HORIZONTAL(Direction.LEFT, Direction.RIGHT),
    CORNER_LEFT_UP(Direction.LEFT, Direction.UP),
    CORNER_LEFT_DOWN(Direction.LEFT, Direction.DOWN),
    CORNER_RIGHT_UP(Direction.RIGHT, Direction.UP),
    CORNER_RIGHT_DOWN(Direction.RIGHT, Direction.DOWN),
    T_UP(Direction.LEFT, Direction.RIGHT, Direction.UP),
    T_DOWN(Direction.LEFT, Direction.RIGHT, Direction.DOWN),
    T_LEFT(Direction.UP, Direction.DOWN, Direction.LEFT),
    T_RIGHT(Direction.UP, Direction.DOWN, Direction.RIGHT),
    CROSS(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT);

    private Set<Direction> directions = new HashSet<>();

    TileType(Direction... directions) {
        this.directions.addAll(Arrays.asList(directions));
    }

    public Set<Direction> getDirections() {
        return new HashSet<>(directions);
    }

    public static TileType fromDirections(Collection<Direction> directions) {
        for (TileType tileType : TileType.values()) {
            if (tileType.directions.size() != directions.size()) continue;
            if (!tileType.directions.containsAll(directions)) continue;
            return tileType;
        }
        return null;
    }
}

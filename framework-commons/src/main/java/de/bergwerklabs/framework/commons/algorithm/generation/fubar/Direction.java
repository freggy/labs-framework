package de.bergwerklabs.framework.commons.algorithm.generation.fubar;

/** Created by Benedikt on 19.06.2017. */
public enum Direction {
  UP,
  LEFT,
  DOWN,
  RIGHT;

  public Direction getOpposite() {
    switch (this) {
      case UP:
        return DOWN;
      case DOWN:
        return UP;
      case LEFT:
        return RIGHT;
      case RIGHT:
        return LEFT;
    }
    throw new RuntimeException("Impossible to happen. Invalid direction.");
  }
}

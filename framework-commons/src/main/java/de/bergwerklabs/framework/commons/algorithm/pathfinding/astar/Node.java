package de.bergwerklabs.framework.commons.algorithm.pathfinding.astar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/** Created by Benedikt on 19.06.2017. */
public abstract class Node<T> implements Comparable<Node> {

  private Node<T> parent = null;
  private T associated = null;

  private double f = 0.0;
  private double g = 0.0;

  public Node(T associated) {
    this.associated = associated;
  }

  public abstract double getHeuristic(Node<T> other);

  /**
   * Any node in the returned array can be null. It will be ignored by the A* algorithm then.
   *
   * @param nodes all nodes important for this path finding cycle
   * @return an array containing all neighbours of this node
   */
  public abstract Set<Node<T>> getSuccessors(Node<T>[] nodes);

  @Override
  public int compareTo(Node other) {
    return Double.compare(f, other.f);
  }

  public T getAssociated() {
    return associated;
  }

  public void setParent(Node<T> parent) {
    this.parent = parent;
  }

  public double getF() {
    return f;
  }

  public void setF(double f) {
    this.f = f;
  }

  public double getG() {
    return g;
  }

  public void setG(double g) {
    this.g = g;
  }

  /**
   * Deconstruct the recursive parent structure to a flat array.
   *
   * @return the flattened array of nodes
   */
  public List<Node<T>> constructPath() {
    final List<Node<T>> nodes = new ArrayList<>();
    Node<T> current = parent;
    nodes.add(this);

    while (current != null) {
      nodes.add(current);
      current = current.parent;
    }

    Collections.reverse(nodes);
    return nodes;
  }
}

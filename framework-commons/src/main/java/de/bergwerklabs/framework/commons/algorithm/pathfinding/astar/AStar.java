package de.bergwerklabs.framework.commons.algorithm.pathfinding.astar;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/** Created by Benedikt on 19.06.2017. */
public class AStar {

  /**
   * Find the shortest path between a given start and end node using the A* algorithm if a path
   * exists.
   *
   * @param nodes all nodes to inspect
   * @param start starting node
   * @param end end node
   * @return a list of nodes representing the shortest path from <code>start</code> to <code>end
   *     </code>. <code>null</code> if there is none
   */
  public static <T> List<Node<T>> findPath(Node<T>[] nodes, Node<T> start, Node<T> end) {
    return findPath(nodes, start, end, new HashSet<>());
  }

  /**
   * Find the shortest path between a given start and end node using the A* algorithm if a path
   * exists.
   *
   * @param nodes all nodes to inspect
   * @param start starting node
   * @param end end node
   * @param excluded nodes to be excluded from inspection
   * @return a list of nodes representing the shortest path from <code>start</code> to <code>end
   *     </code>. <code>null</code> if there is none
   */
  public static <T> List<Node<T>> findPath(
      Node<T>[] nodes, Node<T> start, Node<T> end, Collection<Node<T>> excluded) {
    // We have to reset all parents so that older cycles through the same array won't affect this
    // one.
    for (Node<T> node : nodes) {
      node.setParent(null);
    }

    Queue<Node<T>> openNodes = new PriorityQueue<>();
    Set<Node<T>> closedNodes = new HashSet<>();

    start.setG(0.0);
    start.setF(start.getG() + start.getHeuristic(end));
    openNodes.add(start);

    while (!openNodes.isEmpty()) {
      Node<T> currentNode = openNodes.poll();
      if (currentNode == end) return currentNode.constructPath();

      openNodes.remove(currentNode);
      closedNodes.add(currentNode);

      Set<Node<T>> successors = currentNode.getSuccessors(nodes);

      for (Node<T> successor : successors) {
        if (successor == null || closedNodes.contains(successor) || excluded.contains(successor))
          continue;

        double g = currentNode.getG() + 1;
        double f = g + successor.getHeuristic(end);

        if (!openNodes.contains(successor)) {
          successor.setF(f);
          successor.setG(g);
          successor.setParent(currentNode);
          openNodes.add(successor);
        } else if (g < successor.getG()) {
          successor.setG(g);
          successor.setParent(currentNode);
        }
      }
    }

    return null;
  }
}

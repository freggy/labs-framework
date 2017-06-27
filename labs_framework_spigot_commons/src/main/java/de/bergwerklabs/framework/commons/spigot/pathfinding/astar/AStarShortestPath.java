package de.bergwerklabs.framework.commons.spigot.pathfinding.astar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Created by Yannic Rieger on 05.05.2017.
 * <p> AStar class containing methods for path finding using the A* algorithm. </p>
 * @author Yannic Rieger
 */
public class AStarShortestPath<T> {

    private Comparator<Node> fComparator = (Node x, Node y) -> Double.compare(x.getF(), y.getF()); //ascending to get the lowest

    /**
     * Finds the shortest path between a given start and end node. Using the A* algorithm.
     * @param startNode Node where to start.
     * @param endNode Node where to end.
     * @return a list of nodes representing the shortest path between startNode and endNode, null if no path can be
     * found.
     */
    public ArrayList<Node<T>> findPath(Node<T> startNode, Node<T> endNode) {

        HashSet<Node<T>> closedList = new HashSet<>();
        PriorityQueue<Node<T>> openList = new PriorityQueue<>(this.fComparator);
        ArrayList<Node<T>> path = new ArrayList<>();

        startNode.setCost(0);
        openList.add(startNode);

        while (!openList.isEmpty()) {
            Node<T> currentNode = openList.poll();
            path.add(currentNode);

            if (currentNode.equals(endNode)) return path;

            openList.remove(currentNode);
            closedList.add(currentNode);

            for (Node<T> neighbour : currentNode.getNeighbours()) {
                if (closedList.contains(neighbour)) continue;
                neighbour.setF(neighbour.getCurrentCost(startNode) + neighbour.getHeuristic(endNode));
                if (!openList.contains(neighbour)) openList.add(neighbour);
            }

        }
        return null;
    }
}


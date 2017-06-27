package de.bergwerklabs.framework.commons.spigot.pathfinding.astar;

import java.util.ArrayList;

/**
 * Created by Yannic Rieger on 05.05.2017.
 * <p> This interface should be implemented by classes which represent a node in a graph </p>
 * @author Yannic Rieger
 */
public interface Node<T> {

    /**
     * Current cost from start to this node.
     */
    double getCurrentCost(Node<T> startNode);

    /**
     * Estimated cost from this node tho the end.
     */
    double getHeuristic(Node<T> end);

    /**
     * Value determined by getCurrentCost() + getHeuristic()
     */
    double getF();

    /**
     * Object associated with this node.
     */
    T getAssociatedObject();

    /**
     * Neighbours of this node.
     */
    ArrayList<Node<T>> getNeighbours();

    /**
     * Sets the cost from start to this node.
     * @param cost Value which represents the cost.
     */
    void setCost(double cost);

    /**
     * Set the value determined by getCurrentCost() + getHeuristic()
     * @param f getCurrentCost() + getHeuristic()
     */
    void setF(double f);
}

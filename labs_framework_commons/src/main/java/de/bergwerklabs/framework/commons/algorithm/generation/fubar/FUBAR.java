package de.bergwerklabs.framework.commons.algorithm.generation.fubar;

import de.bergwerklabs.framework.commons.algorithm.pathfinding.astar.AStar;
import de.bergwerklabs.framework.commons.algorithm.pathfinding.astar.Node;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Benedikt on 19.06.2017.
 */
public class FUBAR {

    public static TileType[] generateMap(int boardSize) {
        if (boardSize < 6 || boardSize % 2 != 0) throw new RuntimeException("The board size must be even and at least 6.");

        final int maxIntersections = (boardSize - 2) / 2;

        // Initialize board
        final GridNode[] board = new GridNode[boardSize * boardSize];
        for (int i = 0; i < board.length; i++) {
            board[i] = new GridNode(i / boardSize, i % boardSize, boardSize);
        }

        // STEP 1: Generate base paths
        final Set<Node<GridCoordinate>> centerNodes = new HashSet<>();
        centerNodes.add(board[GridCoordinate.toIndex(boardSize / 2 - 1, boardSize / 2 - 1, boardSize)]);
        centerNodes.add(board[GridCoordinate.toIndex(boardSize / 2 - 1, boardSize / 2, boardSize)]);
        centerNodes.add(board[GridCoordinate.toIndex(boardSize / 2, boardSize / 2 - 1, boardSize)]);
        centerNodes.add(board[GridCoordinate.toIndex(boardSize / 2, boardSize / 2, boardSize)]);

        final Set<Node<GridCoordinate>> excludePathNodes = new HashSet<>(centerNodes);

        final List<List<Node<GridCoordinate>>> paths = new ArrayList<>();

        Node<GridCoordinate> startNode = GridNode.findNode(board,0, (int) (Math.random() * (boardSize - 2) + 1), boardSize);
        Node<GridCoordinate> endNode = GridNode.findNode(board, (int) Math.floor(boardSize / 2.0) - 2, (int) Math.floor(boardSize / 2.0) - 1, boardSize);
        List<Node<GridCoordinate>> path = AStar.findPath(board, startNode, endNode, excludePathNodes);
        if (path == null) return null;
        paths.add(path);
        excludePathNodes.addAll(path);
        addNodeDirections(path);

        startNode = GridNode.findNode(board, (int) (Math.random() * (boardSize - 2) + 1), boardSize - 1, boardSize);
        endNode = GridNode.findNode(board, (int) Math.floor(boardSize / 2.0) - 1, (int) Math.floor(boardSize / 2.0) + 1, boardSize);
        path = AStar.findPath(board, startNode, endNode, excludePathNodes);
        if (path == null) return null;
        paths.add(path);
        excludePathNodes.addAll(path);
        addNodeDirections(path);

        startNode = GridNode.findNode(board, boardSize - 1, (int) (Math.random() * (boardSize - 2) + 1), boardSize);
        endNode = GridNode.findNode(board, (int) Math.floor(boardSize / 2.0) + 1, (int) Math.floor(boardSize / 2.0), boardSize);
        path = AStar.findPath(board, startNode, endNode, excludePathNodes);
        if (path == null) return null;
        paths.add(path);
        excludePathNodes.addAll(path);
        addNodeDirections(path);

        startNode = GridNode.findNode(board, (int) (Math.random() * (boardSize - 2) + 1), 0, boardSize);
        endNode = GridNode.findNode(board, (int) Math.floor(boardSize / 2.0), (int) Math.floor(boardSize / 2.0) - 2, boardSize);
        path = AStar.findPath(board, startNode, endNode, excludePathNodes);
        if (path == null) return null;
        paths.add(path);
        excludePathNodes.addAll(path);
        addNodeDirections(path);

        // STEP 2/3: Generate intersections
        for (int i = 0; i < paths.size(); i++) {
            final int secondIndex = i == paths.size() - 1 ? 0 : i + 1;

            // Determine on which side of the paths the intersections should be added.
            final Direction firstDirection;
            switch (i) {
                case 0: firstDirection = Direction.RIGHT; break;
                case 1: firstDirection = Direction.DOWN; break;
                case 2: firstDirection = Direction.LEFT; break;
                default: firstDirection = Direction.UP;
            }

            final Direction secondDirection;
            switch (secondIndex) {
                case 0: secondDirection = Direction.LEFT; break;
                case 1: secondDirection = Direction.UP; break;
                case 2: secondDirection = Direction.RIGHT; break;
                default: secondDirection = Direction.DOWN;
            }

            // Find all nodes suitable for new intersections in the given direction.
            final List<Node<GridCoordinate>> availableFirst = paths.get(i).stream().filter(node -> !((GridNode) node).hasDirection(firstDirection)).collect(Collectors.toList());
            final List<Node<GridCoordinate>> availableSecond = paths.get(secondIndex).stream().filter(node -> !((GridNode) node).hasDirection(secondDirection)).collect(Collectors.toList());

            Collections.shuffle(availableFirst);
            Collections.shuffle(availableSecond);

            // Generate intersection paths.
            for (int j = 0; j < maxIntersections; j++) {
                if (availableFirst.size() <= j || availableSecond.size() <= j) break;

                Node<GridCoordinate> firstElement = availableFirst.get(j);
                Node<GridCoordinate> secondElement = availableSecond.get(j);

                Node<GridCoordinate> firstNext = ((GridNode) firstElement).findNextNode(board, firstDirection);
                Node<GridCoordinate> secondNext = ((GridNode) secondElement).findNextNode(board, secondDirection);

                ((GridNode) firstElement).addDirection(firstDirection);
                ((GridNode) firstNext).addDirection(firstDirection.getOpposite());
                firstElement = firstNext;

                ((GridNode) secondElement).addDirection(secondDirection);
                ((GridNode) secondNext).addDirection(secondDirection.getOpposite());
                secondElement = secondNext;

                Set<Node<GridCoordinate>> exclude = new HashSet<>(centerNodes);
                exclude.addAll(paths.get(i));
                exclude.remove(firstElement);
                exclude.remove(secondElement);

                List<Node<GridCoordinate>> intersectionPath;
                if (j < maxIntersections / 2) { // STEP 2: Strict intersections
                    intersectionPath = AStar.findPath(board, firstElement, secondElement, exclude);
                } else { // STEP 3: Loose intersections
                    intersectionPath = AStar.findPath(board, firstElement, secondElement, centerNodes);
                }

                addNodeDirections(intersectionPath);
            }
        }

        // STEP 4: Connect loose ends if possible
        for (Node<GridCoordinate> node : board) {
            GridNode gridNode = (GridNode) node;
            if (gridNode.getDirections().size() != 1) continue;

            List<Node<GridCoordinate>> availableNodes = Arrays.stream(Direction.values())
                    .filter(direction -> !gridNode.hasDirection(direction))
                    .map(direction -> gridNode.findNextNode(board, direction))
                    .filter(Objects::nonNull)
                    .filter(it -> ((GridNode) it).getDirections().size() > 0)
                    .collect(Collectors.toList());

            if (availableNodes.size() == 0) continue;

            List<Node<GridCoordinate>> nodes = new ArrayList<>();
            nodes.add(gridNode);
            nodes.add(availableNodes.get((int) (Math.random() * availableNodes.size())));

            addNodeDirections(nodes);
        }

        // STEP 5: Connect center pieces
        board[GridCoordinate.toIndex(boardSize / 2 - 2, boardSize / 2 - 1, boardSize)].addDirection(Direction.DOWN);
        board[GridCoordinate.toIndex(boardSize / 2, boardSize / 2 - 2, boardSize)].addDirection(Direction.RIGHT);
        board[GridCoordinate.toIndex(boardSize / 2 + 1, boardSize / 2, boardSize)].addDirection(Direction.UP);
        board[GridCoordinate.toIndex(boardSize / 2 - 1, boardSize / 2 + 1, boardSize)].addDirection(Direction.LEFT);

        final TileType[] result = new TileType[board.length];
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                int index = GridCoordinate.toIndex(row, col, boardSize);
                result[index] = board[index].getTileType();
            }
        }

        return result;
    }

    private static void addNodeDirections(List<Node<GridCoordinate>> path) {
        for (int i = 0; i < path.size() - 1; i++) {
            GridNode node = (GridNode) path.get(i);
            GridNode other = (GridNode) path.get(i + 1);

            if (node.getAssociated().getCol() < other.getAssociated().getCol()) {
                node.addDirection(Direction.RIGHT);
                other.addDirection(Direction.LEFT);
            } else if (node.getAssociated().getCol() > other.getAssociated().getCol()) {
                node.addDirection(Direction.LEFT);
                other.addDirection(Direction.RIGHT);
            } else if (node.getAssociated().getRow() < other.getAssociated().getRow()) {
                node.addDirection(Direction.DOWN);
                other.addDirection(Direction.UP);
            } else if (node.getAssociated().getRow() > other.getAssociated().getRow()) {
                node.addDirection(Direction.UP);
                other.addDirection(Direction.DOWN);
            }
        }
    }
}

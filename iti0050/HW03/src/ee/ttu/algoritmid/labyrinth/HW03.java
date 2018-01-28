package ee.ttu.algoritmid.labyrinth;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;

public class HW03 {

    private final String DIRECTION_N = "N";
    private final String DIRECTION_S = "S";
    private final String DIRECTION_W = "W";
    private final String DIRECTION_E = "E";
    private final int VISITED = -4;
    private final int UNVISITED = -3;
    private final int TREASURE = -2;
    private final int WALL = -1;
    private final int STARTING_POSITION = 0;
    private int MAZE_HEIGHT;
    private int MAZE_WIDTH;
    private int[][] maze;
    private int[][] mazeVisited;
    private boolean treasureCanBeFound;
    private int bestSolutionBound;
    private List<Point> bestSolutionPoints;

    private MazeRunner mazeRunner;

    public HW03(String fileName) throws IOException, URISyntaxException {
        mazeRunner = new MazeRunner(fileName);
    }

    public MazeRunner getMazeRunner() {
        return mazeRunner;
    }

    /**
     * Returns the list of steps to take to get from beginning ("B") to
     * the treasure ("T"). Uses Depth First Search to explore the maze.
     * After that Best First Search is used to get the best path to the treasure if there is one.
     * This solution is too slow for the two large maze tests.
     *
     * @return return the steps taken as a list of strings (e.g., ["E", "E", "E"])
     * return null if there is no path (there is no way to get to the treasure).
     */
    public List<String> solve() {
        SimpleEntry<Integer, Integer> mazeHeightWidth = mazeRunner.getSize();
        SimpleEntry<Integer, Integer> startPosition = mazeRunner.getPosition();
        MAZE_HEIGHT = mazeHeightWidth.getKey();
        MAZE_WIDTH = mazeHeightWidth.getValue();
        maze = new int[MAZE_HEIGHT][MAZE_WIDTH];
        mazeVisited = new int[MAZE_HEIGHT][MAZE_WIDTH];
        treasureCanBeFound = false;
        bestSolutionBound = Integer.MAX_VALUE;
        for (int[] row : mazeVisited) {
            Arrays.fill(row, UNVISITED);
        }
        exploreMaze(startPosition.getValue(), startPosition.getKey());
        if (treasureCanBeFound) {
            return findBestFirstSolution(startPosition);
        } else {
            return null;
        }
    }


    /**
     * Explores the maze, uses depth first algorithm.
     * Uses bound to somewhat save time for the solution generation later on and on exploring the maze.
     * Should the treasure be found, the function returns to lower level.
     * In each new point the mazeVisited is updated and the map is updated.
     */
    private void exploreMaze(int currentRow, int currentCol) {
        mazeVisited[currentRow][currentCol] = VISITED;
        updateMap(currentRow, currentCol);
        //printMap();
        if (maze[currentRow][currentCol] == TREASURE) {
            treasureCanBeFound = true;
            return;
        }
        //Go north
        if (dfsNewMoveIsValid(currentRow - 1, currentCol)) {
            mazeRunner.move(DIRECTION_N);
            exploreMaze(currentRow - 1, currentCol);
            mazeRunner.move(DIRECTION_S);
        }
        //Go south
        if (dfsNewMoveIsValid(currentRow + 1, currentCol)) {
            mazeRunner.move(DIRECTION_S);
            exploreMaze(currentRow + 1, currentCol);
            mazeRunner.move(DIRECTION_N);
        }
        //Go west
        if (dfsNewMoveIsValid(currentRow, currentCol - 1)) {
            mazeRunner.move(DIRECTION_W);
            exploreMaze(currentRow, currentCol - 1);
            mazeRunner.move(DIRECTION_E);
        }
        //Go east
        if (dfsNewMoveIsValid(currentRow, currentCol + 1)) {
            mazeRunner.move(DIRECTION_E);
            exploreMaze(currentRow, currentCol + 1);
            mazeRunner.move(DIRECTION_W);
        }
    }


    /**
     * Used for debugging.
     */
    private void printMap() {
        System.out.println("--------------------");
        for (int[] row : maze) {
            for (int i = 0; i < row.length; i++) {
                System.out.printf("%2s ", row[i]);
            }
            System.out.println();
        }
        System.out.println("####################");
    }

    private void updateMap(int row, int col) {
        List<List<Integer>> scanArea = mazeRunner.scan();
        for (int i = 0; i < 3; i++) {
            List<Integer> scanAreaRow = scanArea.get(i);
            for (int j = 0; j < 3; j++) {
                if (!coordinatesAreNotWithinBounds(row + i - 1, col + j - 1)) {
                    maze[row + i - 1][col + j - 1] = scanAreaRow.get(j);
                }
            }
        }
    }

    private boolean coordinatesAreNotWithinBounds(int row, int col) {
        return row < 0 || col < 0 || row == MAZE_HEIGHT || col == MAZE_WIDTH;
    }

    private boolean dfsNewMoveIsValid(int newRow, int newCol) {
        if (coordinatesAreNotWithinBounds(newRow, newCol)) {
            return false;
        } else if (maze[newRow][newCol] == WALL || mazeVisited[newRow][newCol] != UNVISITED) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Finds the maze solution with best first algorithm.
     * Best path is updated even when the new path has an equak bound.
     * That is because if the DFS maze exploration should find the best bound,
     * the solution would still be found.
     * */
    private List<String> findBestFirstSolution(SimpleEntry<Integer, Integer> startPosition) {
        bestSolutionPoints = new ArrayList<>();
        PriorityQueue<MazeNode> queue = new PriorityQueue<>(11, new Comparator<MazeNode>() {
            @Override
            public int compare(MazeNode o1, MazeNode o2) {
                return Integer.compare(o1.getBound(), o2.getBound());
            }
        });
        Point rootPoint = new Point(startPosition.getValue(), startPosition.getKey(), STARTING_POSITION);
        queue.add(MazeNode.getRootNode(rootPoint));
        while (!queue.isEmpty()) {
            MazeNode node = queue.poll();
            if (node.getBound() > bestSolutionBound) {
                break;
            }
            for (MazeNode child : getChildrenNodes(node)) {
                if (isSolution(child)) {
                    if (child.getBound() <= bestSolutionBound) {
                        bestSolutionPoints = child.getPoints();
                        bestSolutionBound = child.getBound();
                    }
                } else if (child.getBound() <= bestSolutionBound) {
                    queue.add(child);
                }
            }
        }
        return convertPointsToDirections(bestSolutionPoints);
    }

    /**
     * Used to generate chilren nodes for a best first algorithm node.
     * */
    private List<MazeNode> getChildrenNodes(MazeNode node) {
        List<MazeNode> children = new ArrayList<>();
        int currentRow = node.getCurrentPoint().getRow();
        int currentCol = node.getCurrentPoint().getCol();

        // Go north
        if (bfsNewMoveIsValid(currentRow - 1, currentCol, node)) {
            int value = maze[currentRow - 1][currentCol];
            children.add(new MazeNode(new Point(currentRow - 1, currentCol, value), node));
        }
        // Go south
        if (bfsNewMoveIsValid(currentRow + 1, currentCol, node)) {
            int value = maze[currentRow + 1][currentCol];
            children.add(new MazeNode(new Point(currentRow + 1, currentCol, value), node));
        }
        // Go west
        if (bfsNewMoveIsValid(currentRow, currentCol + 1, node)) {
            int value = maze[currentRow][currentCol + 1];
            children.add(new MazeNode(new Point(currentRow, currentCol + 1, value), node));
        }
        // Go east
        if (bfsNewMoveIsValid(currentRow, currentCol - 1, node)) {
            int value = maze[currentRow][currentCol - 1];
            children.add(new MazeNode(new Point(currentRow, currentCol - 1, value), node));
        }
        return children;
    }

    private boolean bfsNewMoveIsValid(int newRow, int newCol, MazeNode node) {
        if (coordinatesAreNotWithinBounds(newRow, newCol)) {
            return false;
        } else if (maze[newRow][newCol] == WALL || node.containsPointCoordinates(newRow, newCol)) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isSolution(MazeNode mazeNode) {
        return mazeNode.getCurrentPoint().getValue() == TREASURE;
    }

    private List<String> convertPointsToDirections(List<Point> bestSolutionPoints) {
        List<String> directions = new ArrayList<>();
        for (int i = 0; i < bestSolutionPoints.size() - 1; i++) {
            Point currentPoint = bestSolutionPoints.get(i);
            Point nextPoint = bestSolutionPoints.get(i + 1);
            if (currentPoint.getRow() - nextPoint.getRow() == 1) {
                directions.add(DIRECTION_N);
            } else if (nextPoint.getRow() - currentPoint.getRow() == 1) {
                directions.add(DIRECTION_S);
            } else if (currentPoint.getCol() - nextPoint.getCol() == 1) {
                directions.add(DIRECTION_W);
            } else {
                directions.add(DIRECTION_E);
            }
        }
        return directions;
    }
}

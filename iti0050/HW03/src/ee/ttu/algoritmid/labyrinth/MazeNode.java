package ee.ttu.algoritmid.labyrinth;

import java.util.ArrayList;
import java.util.List;

public class MazeNode {

    private int bound;
    private List<Point> points;
    private final int TREASURE = -2;


    private MazeNode() {
        points = new ArrayList<>();
    }

    static MazeNode getRootNode(Point rootPoint) {
        MazeNode root = new MazeNode();
        root.points.add(rootPoint);
        root.bound = 0;
        return root;
    }

    MazeNode(Point newPoint, MazeNode previousMazeNode) {
        points = new ArrayList<>(previousMazeNode.getPoints());
        points.add(newPoint);
        if (newPoint.getValue() != TREASURE) {
            bound = previousMazeNode.getBound() + newPoint.getValue();
        } else {
            bound = previousMazeNode.getBound();
        }
    }

    Point getCurrentPoint() {
        return points.get(points.size() - 1);
    }

    boolean containsPointCoordinates(int row, int col) {
        // start from end because new coordinates are most likely to appear in the end of the list
        for (int i = points.size() - 1; i >= 0; i--) {
            if (points.get(i).getRow() == row && points.get(i).getCol() == col) {
                return true;
            }
        }
        return false;
    }

    int getBound() {
        return bound;
    }

    List<Point> getPoints() {
        return points;
    }


}

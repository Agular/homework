package ee.ttu.algoritmid.tsp.node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BreachBoundNode {

    public static final int UNVISITED = -1;
    public static final int VISITED = 1;

    private int value;
    private int valueIdx;
    private int[] visited;
    private int[] unvisited;
    private int bound;

    public BreachBoundNode(int value, int valueIdx) {
        this.value = value;
        this.valueIdx = valueIdx;
    }

    public static BreachBoundNode getRootNode(int N) {
        BreachBoundNode rootNode = new BreachBoundNode(0, 0);
        rootNode.unvisited = new int[N];
        rootNode.visited = new int[N + 1];
        Arrays.fill(rootNode.unvisited, UNVISITED);
        Arrays.fill(rootNode.visited, -1);
        rootNode.visited[0] = 0;
        rootNode.unvisited[0] = VISITED;
        return rootNode;
    }

    public List<BreachBoundNode> getChildrenNodes() {
        List<BreachBoundNode> children = new ArrayList<>();
        for (int i = 0; i < unvisited.length; i++) {
            if (unvisited[i] == UNVISITED) {
                BreachBoundNode child = new BreachBoundNode(i, valueIdx + 1);
                child.unvisited = unvisited.clone();
                child.visited = visited.clone();
                child.unvisited[i] = VISITED;
                child.visited[valueIdx + 1] = i;
                children.add(child);
            }
        }
        return children;
    }

    public boolean isSolution() {
        return visited[visited.length - 2] != UNVISITED;
    }

    public int getValue() {
        return value;
    }

    public int[] getVisited() {
        return visited;
    }

    public void setVisited(int[] visited) {
        this.visited = visited;
    }

    public int[] getUnvisited() {
        return unvisited;
    }

    public void setUnvisited(int[] unvisited) {
        this.unvisited = unvisited;
    }

    public void setBound(int bound) {
        this.bound = bound;
    }

    public int getBound() {
        return bound;
    }

    public int getValueIdx() {
        return valueIdx;
    }
}

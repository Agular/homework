package ee.ttu.algoritmid.tsp;

import ee.ttu.algoritmid.tsp.node.BreachBoundNode;

import java.math.BigInteger;
import java.util.*;

public class TSP {

    private int N;
    private int[] visited;
    private int[] bestRoad;
    private int[] minPaths;
    private int bestRoadLength;
    private int[][] adjacencyMatrix;
    private BigInteger checkedNodesCount;

    /* Depth first search */
    public List<Integer> depthFirst(int[][] adjacencyMatrix) {
        if (adjacencyMatrix.length == 0) {
            return new ArrayList<>();
        } else if (adjacencyMatrix.length == 1) {
            checkedNodesCount = BigInteger.ONE;
            return Collections.singletonList(0);
        }
        this.adjacencyMatrix = adjacencyMatrix;
        N = adjacencyMatrix.length;
        checkedNodesCount = BigInteger.ZERO;
        bestRoad = greedySolution();
        bestRoadLength = roadLength(bestRoad);
        visited = getNewVisitedArray();
        visited[0] = 0;
        calculateMinimumPaths();
        tspDepthFirst(0);

        System.out.println("Best road length: " + bestRoadLength);
        return convertArrayToList(bestRoad);
    }

    private void calculateMinimumPaths() {
        minPaths = new int[N];
        for (int i = 0; i < N; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < N; j++) {
                int edgeLength = adjacencyMatrix[i][j];
                if (edgeLength != 0 && edgeLength < min) {
                    min = edgeLength;
                }
            }
            minPaths[i] = min;
        }
    }

    private void tspDepthFirst(int visitedArv) {
        checkedNodesCount = checkedNodesCount.add(BigInteger.ONE);
        if (visitedArv == N - 1) {
            visited[N] = visited[0];
            int roadLength = roadLength(visited); //koos teega algusse
            if (roadLength < bestRoadLength) {
                bestRoadLength = roadLength;
                bestRoad = visited.clone(); // tuleb teha koopia
            }
        } else if (bound(visited) < bestRoadLength) {
            for (int i = 0; i < N; i++) {
                if (!valueIsInVisited(i, visited)) {
                    visited[visitedArv + 1] = i;
                    tspDepthFirst(visitedArv + 1);
                    visited[visitedArv + 1] = -1;
                }
            }
        }
    }

    private int bound(int[] visited) {
        int visitedLastElementIdx = -1;
        int bound = 0;
        int[] unvisited = new int[N];
        Arrays.fill(unvisited, -1);
        bound += roadLength(visited);  // the length  of the road travelled so far
        for (int i = 0; i < N; i++) {
            if (visited[i] == -1) {
                visitedLastElementIdx = i - 1;
                break;
            }
            unvisited[visited[i]] = 1;
        }

        for (int i = 0; i < N; i++) {
            if (unvisited[i] == -1 || visited[visitedLastElementIdx] == i && visited[visitedLastElementIdx + 1] == -1) {
                bound += minPaths[i];
            }
        }
        return bound;
    }

    /* Best first search */
    public List<Integer> bestFirst(int[][] adjacencyMatrix) {
        if (adjacencyMatrix.length == 0) {
            return new ArrayList<>();
        } else if (adjacencyMatrix.length == 1) {
            checkedNodesCount = BigInteger.ONE;
            return Collections.singletonList(0);
        }
        this.adjacencyMatrix = adjacencyMatrix;
        N = adjacencyMatrix.length;
        checkedNodesCount = BigInteger.ZERO;
        bestRoad = greedySolution();
        bestRoadLength = roadLength(bestRoad);
        calculateMinimumPaths();

        PriorityQueue<BreachBoundNode> queue = new PriorityQueue<>(4, new Comparator<BreachBoundNode>() {
            @Override
            public int compare(BreachBoundNode o1, BreachBoundNode o2) {
                return Integer.compare(o1.getBound(), o2.getBound());
            }
        });
        BreachBoundNode v = BreachBoundNode.getRootNode(N);
        queue.add(v);
        while (!queue.isEmpty()) {
            BreachBoundNode node = queue.poll();
            checkedNodesCount = checkedNodesCount.add(BigInteger.ONE);
            if (breachBoundNodeBound(node) >= bestRoadLength) {
                break;
            }
            for (BreachBoundNode child : node.getChildrenNodes()) {
                checkedNodesCount = checkedNodesCount.add(BigInteger.ONE);
                if (child.isSolution()) {
                    int[] temporaryBestRoad = child.getVisited();
                    temporaryBestRoad[N] = 0;
                    int temporaryBestRoadLength = roadLength(temporaryBestRoad);
                    if (temporaryBestRoadLength < bestRoadLength) {
                        bestRoad = temporaryBestRoad;
                        bestRoadLength = temporaryBestRoadLength;
                    }
                } else if (breachBoundNodeBound(child) < bestRoadLength) {
                    queue.add(child);
                }
            }
        }
        System.out.println("Best road length: " + bestRoadLength);
        return convertArrayToList(bestRoad);
    }

    private int breachBoundNodeBound(BreachBoundNode node) {
        int bound = 0;
        bound += roadLength(node.getVisited());
        int[] unvisited = node.getUnvisited();
        for (int i = 0; i < unvisited.length; i++) {
            if (unvisited[i] == BreachBoundNode.UNVISITED /*|| node.getVisited()[node.getValueIdx()] == i && node.getVisited()[node.getValueIdx() + 1] == -1*/) {
                bound += minPaths[i];
            }
        }
        node.setBound(bound);
        return bound;
    }

    /* Nodes viewed in last matrix to find the solution (should be zeroed at the beginning of search) */

    public BigInteger getCheckedNodesCount() {
        return checkedNodesCount;
    }


    // Greedy solution to make grading faster.
    private int[] greedySolution() {
        int current = 0;
        int[] greedyPath = getNewVisitedArray();
        greedyPath[N] = 0;
        for (int i = 0; i < N; i++) {
            greedyPath[i] = current;
            int min = Integer.MAX_VALUE;
            int minIdx = -1;
            for (int j = 1; j < N; j++) {
                if (adjacencyMatrix[current][j] < min && !valueIsInVisited(j, greedyPath)) {
                    min = adjacencyMatrix[current][j];
                    minIdx = j;
                }
            }
            current = minIdx;
        }
        checkedNodesCount = checkedNodesCount.add(BigInteger.valueOf(adjacencyMatrix.length));
        return greedyPath;
    }

    private int roadLength(int[] visited) {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            if (visited[i + 1] == -1) {
                break;
            }
            sum += adjacencyMatrix[visited[i]][visited[i + 1]];
        }
        return sum;
    }

    private List<Integer> convertArrayToList(int[] array) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    private boolean valueIsInVisited(int value, int[] visited) {
        for (int i = 0; i < N + 1; i++) {
            if (visited[i] == value) {
                return true;
            }
        }
        return false;
    }

    private int[] getNewVisitedArray() {
        int[] newVisitedArray = new int[N + 1];
        Arrays.fill(newVisitedArray, -1);
        return newVisitedArray;
    }
}
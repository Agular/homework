package ee.ttu.algoritmid.tsp;

import java.util.Arrays;

public class GreedyTSP {

    /* Greedy search */

    /*
    * int läbitud[N]; // läbitud linnade numbrid läbimise järjekorras
int läbimata[N]; // läbimata[i]=0 kui linn i läbimata
int parim_tee[N] = ahne_hinnang();
int parim_teepikkus = teepikkus(parim_tee);
tsp(int läbitud_arv) {
if(läbitud_arv == N){
teepikkus = teepikkus(läbitud); // koos teega algusse
if(teepikkus < parim_teepikkus){
parim_teepikkus = teepikkus;
parim_tee = läbitud; // tuleb teha koopia
}
elif(bound(läbitud, läbimata) < parim_teepikkus)
for i in läbimata {
läbitud[läbitud_arv+1] = i;
läbimata[i] = 1;
tsp(läbitud_arv+1);
}
}
    * */
    private static int N;
    private static int visited[];
    private static int unvisited[];
    private static int bestPathLength;
    private static int bestPath[];
    private static int current;
    private static int[][] adjacencyMatrix;

    public static int[] greedySolution(int[][] adjacencyMatrix) {
        GreedyTSP.adjacencyMatrix = adjacencyMatrix;
        N = adjacencyMatrix.length;
        bestPathLength = Integer.MAX_VALUE;
        bestPath = greedyGrading(adjacencyMatrix);

        for (int i = 0; i < N; i++) {
            current = i;
            visited = new int[N + 1];
            unvisited = new int[N];
            tsp(0);
            visited[N] = i;
            int pathLength = getPathLength(visited);
            if (pathLength < bestPathLength) {
                bestPathLength = pathLength;
                bestPath = visited;
            }
        }
        return bestPath;
    }

    private static void tsp(int visitedNodes) {
        if (visitedNodes == N) {
            int pathLength = getPathLength(visited);
            if (pathLength < bestPathLength) {
                bestPathLength = pathLength;
                bestPath = visited;
            }
        } else {
            unvisited[current] = 1;
            visited[visitedNodes] = current;
            while (Arrays.stream(unvisited).sum() != N) {
                int minValue = Integer.MAX_VALUE;
                int minIdx = Integer.MAX_VALUE;
                for (int i = 0; i < N; i++) {
                    if (GreedyTSP.adjacencyMatrix[current][i] < minValue && unvisited[i] == 0 && i != current) {
                        minValue = GreedyTSP.adjacencyMatrix[current][i];
                        minIdx = i;
                    }
                }
                current = minIdx;
                tsp(visitedNodes + 1);
            }
        }
    }

    private static int[] greedyGrading(int[][] adjacencyMatrix) {
        int[] greedyBestPath = new int[N + 1];
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            int min = Integer.MAX_VALUE;
            int minIdx = 0;
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                if (i != j && adjacencyMatrix[i][j] < min) {
                    min = adjacencyMatrix[i][j];
                    minIdx = j;
                }
            }
            greedyBestPath[i] = minIdx;
        }
        return greedyBestPath;
    }

    private static int getPathLength(int[] bestPath) {
        int sum = 0;
        for (int i = 0; i < bestPath.length - 1; i++) {
            sum += GreedyTSP.adjacencyMatrix[bestPath[i]][bestPath[i + 1]];
        }
        return sum;
    }
}

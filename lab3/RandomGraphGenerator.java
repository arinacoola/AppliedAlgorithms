import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class RandomGraphGenerator {
    public static int[][] generateMatrixGraph(int n, double p) {
        int[][] matrix = new int[n][n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (rand.nextDouble() < p) {
                    matrix[i][j] = 1;
                    matrix[j][i] = 1;
                } else {
                    matrix[i][j] = 0;
                    matrix[j][i] = 0;
                }
            }
        }
        return matrix;
    }

    public static List<List<Integer>> toAdjList(int[][] matrix) {
        int n = matrix.length;
        List<List<Integer>> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            List<Integer> neighbors = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != 0) {
                    neighbors.add(j);
                }
            }
            list.add(neighbors);
        }
        return list;
    }
}



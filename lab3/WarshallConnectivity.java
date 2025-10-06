public class WarshallConnectivity {
    public static boolean isConnected(int[][] graph) {
        int n = graph.length;
        int[][] reach = new int[n][n];

        for (int i = 0; i < n; i++) System.arraycopy(graph[i], 0, reach[i], 0, n);

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                if (reach[i][k] == 0) continue;
                for (int j = 0; j < n; j++) {
                    if (reach[i][j] == 0 && reach[k][j] != 0) {
                        reach[i][j] = 1;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (i != j && reach[i][j] == 0) {
                    return false;
                }

        return true;
    }
}

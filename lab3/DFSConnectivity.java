import java.util.List;

public class DFSConnectivity {

    public static boolean isConnected(int[][] graph) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        dfsMatrix(graph, 0, visited);

        for (boolean v : visited) {
            if (!v) {
                return false;
            }
        }
        return true;
    }

    private static void dfsMatrix(int[][] graph, int v, boolean[] visited) {
        visited[v] = true;
        for (int i = 0; i < graph.length; i++) {
            if (graph[v][i] != 0 && !visited[i]) {
                dfsMatrix(graph, i, visited);
            }
        }
    }

    public static boolean isConnected(List<List<Integer>> adjList) {
        int n = adjList.size();
        boolean[] visited = new boolean[n];
        dfsList(adjList, 0, visited);

        for (boolean v : visited) {
            if (!v){
                return false;
            }
        }
        return true;
    }

    private static void dfsList(List<List<Integer>> adjList, int v, boolean[] visited) {
        visited[v] = true;
        for (int u : adjList.get(v)) {
            if (!visited[u]) {
                dfsList(adjList, u, visited);
            }
        }
    }
}

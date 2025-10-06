import java.util.List;

public class Test3 {
    public static void main(String[] args) {
        int[] sizes = {5, 10, 20, 50};
        double[] densities = {0, 0.2, 0.5, 0.8, 1.0};
        int trials = 100;

        System.out.println("n\t p\t   DFS_M\tDFS_M_con\t  DFS_L\tDFS_L_con\t  Warshall\tWarshall_con");

        int[][] warmGraph = RandomGraphGenerator.generateMatrixGraph(10, 0.5);
        DFSConnectivity.isConnected(warmGraph);
        DFSConnectivity.isConnected(RandomGraphGenerator.toAdjList(warmGraph));
        WarshallConnectivity.isConnected(warmGraph);

        for (int n : sizes) {
            for (double p : densities) {
                long dfsMatrixTime = 0;
                long dfsListTime = 0;
                long warshallTime = 0;

                boolean dfsMatrixResult = true;
                boolean dfsListResult = true;
                boolean warshallResult = true;

                int[][][] graphs = new int[trials][][];
                List<List<Integer>>[] adjLists = new List[trials];

                for (int t = 0; t < trials; t++) {
                    graphs[t] = RandomGraphGenerator.generateMatrixGraph(n, p);
                    adjLists[t] = RandomGraphGenerator.toAdjList(graphs[t]);
                }

                for (int t = 0; t < trials; t++) {
                    int[][] g = graphs[t];
                    List<List<Integer>> adj = adjLists[t];

                    long start = System.nanoTime();
                    dfsMatrixResult = DFSConnectivity.isConnected(g);
                    dfsMatrixTime += System.nanoTime() - start;

                    start = System.nanoTime();
                    dfsListResult = DFSConnectivity.isConnected(adj);
                    dfsListTime += System.nanoTime() - start;

                    start = System.nanoTime();
                    warshallResult = WarshallConnectivity.isConnected(g);
                    warshallTime += System.nanoTime() - start;
                }

                System.out.printf("%-3d  %-5.1f  %-8.2f  %-9s  %-8.2f  %-9s  %-8.2f  %-9s\n",
                        n, p,
                        dfsMatrixTime / 1e3 / trials, dfsMatrixResult,
                        dfsListTime / 1e3 / trials, dfsListResult,
                        warshallTime / 1e3 / trials, warshallResult);
            }
        }
    }
}

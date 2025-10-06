import java.util.List;

public class Test3 {
    public static void main(String[] args) {
        int[] sizes = {5, 10, 20, 50};
        double[] densities = {0, 0.2, 0.5, 0.8, 1.0};
        int trials = 100;

        System.out.println("n\t p\t   DFS_M\t  DFS_L\t  Warshall");

        int[][] warmGraph = RandomGraphGenerator.generateMatrixGraph(10, 0.5);
        DFSConnectivity.isConnected(warmGraph);
        DFSConnectivity.isConnected(RandomGraphGenerator.toAdjList(warmGraph));
        WarshallConnectivity.isConnected(warmGraph);

        for (int n : sizes) {
            for (double p : densities) {
                long dfsMatrixTime = 0;
                long dfsListTime = 0;
                long warshallTime = 0;

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
                    DFSConnectivity.isConnected(g);
                    dfsMatrixTime += System.nanoTime() - start;

                    start = System.nanoTime();
                    DFSConnectivity.isConnected(adj);
                    dfsListTime += System.nanoTime() - start;

                    start = System.nanoTime();
                    WarshallConnectivity.isConnected(g);
                    warshallTime += System.nanoTime() - start;
                }

                System.out.printf("%-3d  %-5.1f  %-8.2f  %-8.2f  %-8.2f\n",
                        n, p,
                        dfsMatrixTime / 1e3 / trials,
                        dfsListTime / 1e3 / trials,
                        warshallTime / 1e3 / trials);
            }
        }
    }
}

import java.io.PrintWriter;
import java.util.*;

public class Main1 {
    private static List<Edge1> generateRandomGraphEdges(int n, int m, long maxWeight, Random rnd) {
        if (m > (long) n * (n - 1) / 2) {
            throw new IllegalArgumentException("too many edges for simple undirected graph");
        }

        Set<Long> used = new HashSet<>();
        List<Edge1> edges = new ArrayList<>();
        while (edges.size() < m) {
            int u = rnd.nextInt(n);
            int v = rnd.nextInt(n);
            if (u == v) continue;
            int a = Math.min(u, v);
            int b = Math.max(u, v);
            long key = ((long) a << 32) | (b & 0xffffffffL);
            if (used.contains(key)) continue;
            used.add(key);
            long w = 1 + (Math.abs(rnd.nextLong()) % maxWeight);
            edges.add(new Edge1(u, v, w));
        }
        return edges;
    }

    public static void main(String[] args) {
        Random rnd = new Random(12345);
        int n = 1000;
        int m = 5000;
        long maxWeight = 1_000_000L;

        List<Edge1> edges = generateRandomGraphEdges(n, m, maxWeight, rnd);
        Collections.sort(edges);

        Kruskal kr = new Kruskal(n, edges);
        KruskalResult res = kr.buildMST(true);

        System.out.println(res);
        int[] vertexCounts = {100, 300, 500, 800, 1000, 1500, 2000};

        try (PrintWriter pw = new PrintWriter("results1.csv")) {
            pw.println("n,m,MST_weight,Time_ms");

            for (int nVal : vertexCounts) {
                int mVal = nVal * 5;
                List<Edge1> edges2 = generateRandomGraphEdges(nVal, mVal, maxWeight, rnd);
                Collections.sort(edges2);

                Kruskal kr2 = new Kruskal(nVal, edges2);
                KruskalResult res2 = kr2.buildMST(true);

                pw.printf("%d,%d,%d,%.6f\n",
                        nVal, mVal, res2.getTotalWeight(), res2.getElapsedNs() / 1e6);

                System.out.printf("n=%d, m=%d → %.6f ms\n",
                        nVal, mVal, res2.getElapsedNs() / 1e6);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

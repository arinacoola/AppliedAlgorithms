import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kruskal {
    private final int n;
    private final List<Edge1> edges;

    public Kruskal(int n, List<Edge1> edges) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be >= 0");
        }
        this.n = n;
        this.edges = new ArrayList<>(edges);
    }

    public KruskalResult buildMST(boolean edgesAlreadySorted) {
        List<Edge1> edgesToProcess = this.edges;
        if (!edgesAlreadySorted) {
            Collections.sort(edgesToProcess);
        }

        UnionFindList uf = new UnionFindList(n);
        List<Edge1> mst = new ArrayList<>();
        long totalWeight = 0;

        long start = System.nanoTime();

        for (Edge1 e : edgesToProcess) {
            if (mst.size() == n - 1) break;
            int u = e.getU();
            int v = e.getV();
            if (!uf.sameSet(u, v)) {
                uf.union(u, v);
                mst.add(e);
                totalWeight += e.getW();
            }
        }

        long end = System.nanoTime();
        long elapsedNs = end - start;

        return new KruskalResult(mst, totalWeight, elapsedNs);
    }
}
import java.util.List;

public class KruskalResult {
    private final List<Edge1> mst;
    private final long totalWeight;
    private final long elapsedNs;

    public KruskalResult(List<Edge1> mst, long totalWeight, long elapsedNs) {
        this.mst = mst;
        this.totalWeight = totalWeight;
        this.elapsedNs = elapsedNs;
    }

    public List<Edge1> getMst() {
        return mst;
    }

    public long getTotalWeight() {
        return totalWeight;
    }

    public long getElapsedNs() {
        return elapsedNs;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MST total weight: ").append(totalWeight).append("\n");
        sb.append("Edges in MST (").append(mst.size()).append("):\n");
        for (Edge1 e : mst) {
            sb.append(e).append("\n");
        }
        sb.append(String.format("Elapsed time (ms): %.6f\n", elapsedNs / 1e6));
        return sb.toString();
    }
}
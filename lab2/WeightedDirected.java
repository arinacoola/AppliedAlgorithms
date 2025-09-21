import java.util.LinkedList;
import java.util.Random;
import java.util.stream.IntStream;

public class WeightedDirected extends Graph<Edge>{
    public WeightedDirected(int n){
        super(n);
    }

    @Override
    public void addEdge(int u, int v) {
        throw new UnsupportedOperationException("there is no weight parameter for a weighted graph");
    }

    void addEdge(int u,int v,int weight){
        checkVertex(u);
        checkVertex(v);
        adjLists[u].add(new Edge(v,weight));
    }

    @Override
    public void deleteVertex(int u){
        checkVertex(u);
        for (int i = 0; i < n; i++) {
            if (i == u) continue;
            for(int j = adjLists[i].size() - 1; j >= 0; j--){
                Edge edge = adjLists[i].get(j);
                if (edge.to == u) {
                    adjLists[i].remove(j);
                } else if (edge.to > u) {
                    edge.to = edge.to - 1;
                }
            }
        }
        for (int i = u; i < n - 1; i++) {
            adjLists[i] = adjLists[i + 1];
        }
        adjLists[n - 1] = null;
        n--;

    }

    void deleteEdge(int u, int v) {
        checkVertex(u);
        checkVertex(v);
        adjLists[u].removeIf(edge -> edge.to == v);
    }

    public  int[][] convertToAdjMatrixW(){
        int[][] adjMatrix = new int[n][n];
        for(int i =0;i<n;i++){
            for( Edge edge:adjLists[i]){
                adjMatrix[i][edge.to]=edge.weight;
            }
        }
        return adjMatrix;
    }

    void BackAdjMatrixW(int [][] adjMatrix){
        for (int i = 0; i < n; i++) {
            adjLists[i] = new LinkedList<>();
            for (int j = 0; j < n; j++) {
                if (adjMatrix[i][j] != 0) {
                    adjLists[i].add(new Edge(j, adjMatrix[i][j]));
                }
            }
        }
    }

    void erdosRenyiWeighted(int n, float p,int minWeight,int maxWeight ) {
        this.n = n;
        adjLists = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            adjLists[i] = new LinkedList<>();
        }
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && random.nextFloat() < p) {
                    int newWeight = random.nextInt(maxWeight - minWeight + 1) + minWeight;
                    addEdge(i, j,newWeight);
                }
            }

        }
    }

}

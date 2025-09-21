import java.util.LinkedList;
import java.util.Random;

public class WeightedUndirected extends Graph<Edge>{
    public WeightedUndirected(int n){
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
        adjLists[v].add(new Edge(u,weight));
    }

    void deleteEdge(int u,int v){
        checkVertex(u);
        checkVertex(v);
        adjLists[u].removeIf(edge -> edge.to == v);
        adjLists[v].removeIf(edge -> edge.to == u);
    }

    @Override
    public void deleteVertex(int u) {
        checkVertex(u);
        for (int i = 0; i < n; i++) {
            if (i == u) continue;
            adjLists[i].removeIf(edge -> edge.to == u);
            for (Edge edge : adjLists[i]) {
                if (edge.to > u) edge.to--;
            }
        }
        for (int i = u; i < n - 1; i++) adjLists[i] = adjLists[i + 1];
        adjLists[n - 1] = null;
        n--;
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

    public void BackAdjMatrixW(int [][] adjMatrix) {
        for (int i = 0; i < n; i++) {
            adjLists[i] = new LinkedList<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (adjMatrix[i][j] != 0) {
                    addEdge(i, j, adjMatrix[i][j]);
                }
            }

        }
    }

    public void erdosRenyiWeighted(int n, float p, int minWeight, int maxWeight){
        this.n = n;
        adjLists = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            adjLists[i] = new LinkedList<>();
        }
        Random random = new Random();
        for (int i = 0; i < n; i++){
            for (int j = i + 1; j < n; j++){
                if(random.nextFloat() < p){
                    int weight = random.nextInt(maxWeight - minWeight + 1) + minWeight;
                    addEdge(i, j, weight);
                }
            }
        }
    }
}







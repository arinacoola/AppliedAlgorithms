import java.util.LinkedList;
import java.util.Random;

public class WeightedUndirected extends Graph<Edge>{
    public WeightedUndirected(int n){
        super(n);
    }

    @Override
    void addEdge(int u, int v) {

    }

    void addEdge(int u,int v,int weight){
        checkVertex(u);
        checkVertex(v);
        adjLists[u].add(new Edge(v,weight));
        adjLists[v].add(new Edge(u,weight));
    }

    void deleteEdgeW(int u,int v){
        checkVertex(u);
        checkVertex(v);
        for(int i=adjLists[u].size()-1;i>=0;i--){
            Edge edge = adjLists[u].get(i);
            if(edge.to==v){
                adjLists[u].remove(i);
            }
        }
        for(int i=adjLists[v].size()-1;i>=0;i--){
            Edge edge = adjLists[v].get(i);
            if(edge.to==u){
                adjLists[v].remove(i);
            }
        }
    }

    public  int[][] convertToAdjMatrixW(){
        int[][] adjMatrix = new int[n][n];
        for(int i =0;i<n;i++){
            for( Edge edge:adjLists[i]){
                adjMatrix[i][edge.to]=edge.weight;
                adjMatrix[edge.to][i]=edge.weight;
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
                    if (i != j) {
                        adjLists[j].add(new Edge(i, adjMatrix[i][j]));
                    }
                }
            }
        }
    }
    void erdosRenyiW(int n, float p,int minWeight,int maxWeight ) {
        this.n = n;
        adjLists = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            adjLists[i] = new LinkedList<>();
        }
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                float edgeP = random.nextFloat();
                if (edgeP < p) {
                    int newWeight = random.nextInt(maxWeight-minWeight+1)+maxWeight;
                    addEdge(i, j,newWeight);
                    addEdge(j,i,newWeight);
                }
            }

        }


    }


}

import java.util.LinkedList;

public class Undirected extends Graph<Integer>{
     public Undirected(int n) {
          super(n);
     }

     void addEdge(int u, int v){
              checkVertex(u);
              checkVertex(v);
              adjLists[u].add(v);
              adjLists[v].add(u);
     }

     void deleteEdge(int u,int v){
         checkVertex(u);
         checkVertex(v);
         for(int i=adjLists[u].size()-1;i>=0;i--){
             int nextV = (int) adjLists[u].get(i);
             if(nextV==v){
                 adjLists[u].remove(i);
             }
         }
         for(int i = adjLists[v].size()-1;i>=0;i--){
             int nextV = (int) adjLists[v].get(i);
             if (nextV ==u){
                 adjLists[v].remove(i);
             }
         }
     }

    public  int[][] convertToAdjMatrix() {
        int[][] adjMatrix = new int[n][n];
        for (int i =0;i<n;i++){
            for( int j:adjLists[i]){
                adjMatrix[i][j]=1;
                adjMatrix[j][i]=1;
            }
        }
        return adjMatrix;
    }

    void BackAdjMatrix(int [][] adjMatrix){
        for (int i = 0; i < n; i++) {
            adjLists[i] = new LinkedList<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adjMatrix[i][j] != 0) {
                    adjLists[i].add(j);
                    adjLists[j].add(i);
                }
            }
        }


    }



}

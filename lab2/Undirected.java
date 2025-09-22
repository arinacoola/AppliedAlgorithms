import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Undirected extends Graph<Integer>{
     public Undirected(int n) {
          super(n);
     }

     public void addEdge(int u, int v){
              checkVertex(u);
              checkVertex(v);
              adjLists[u].add(v);
              adjLists[v].add(u);
     }

    public void deleteEdge(int u,int v){
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


    public void erdosRenyi(int n, float p) {
        this.n = n;
        adjLists = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            adjLists[i] = new LinkedList<>();
        }
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (random.nextFloat() < p)
                    addEdge(i, j);
            }
        }

    }

        public List<String> exportEdgesAsJson() {
            List<String> result = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                for (Integer j : adjLists[i]) {
                    if (i < j) result.add("[" + i + "," + j + "]");
                }
            }
            return result;

    }





}

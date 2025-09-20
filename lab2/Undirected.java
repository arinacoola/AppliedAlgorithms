public class Undirected extends Graph{
     public Undirected(int n) {
          super(n);
     }

     void addEdge(int u, int v){
              checkVertex(u);
              checkVertex(v);
              adjLists[u].add(v);
              adjLists[v].add(u);
     }


}

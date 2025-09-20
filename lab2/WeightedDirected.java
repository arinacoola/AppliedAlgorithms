import java.util.LinkedList;
public class WeightedDirected extends Graph<Edge>{
    public WeightedDirected(int n){
        super(n);
    }


    void addEdge(int u,int v,int weight){
        checkVertex(u);
        checkVertex(v);
        adjLists[u].add(new Edge(v,weight));
    }
}

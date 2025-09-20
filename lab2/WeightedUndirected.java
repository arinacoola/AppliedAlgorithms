public class WeightedUndirected extends Graph<Edge>{
    public WeightedUndirected(int n){
        super(n);
    }
    void addEdge(int u,int v,int weight){
        checkVertex(u);
        checkVertex(v);
        adjLists[u].add(new Edge(v,weight));
        adjLists[v].add(new Edge(u,weight));
    }
}

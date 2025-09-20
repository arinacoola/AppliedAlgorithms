public class Directed extends Graph{
    public Directed(int n){
        super(n);
    }

    void addEdge(int u, int v){
        checkVertex(u);
        checkVertex(v);
        adjLists[u].add(v);
    }
}

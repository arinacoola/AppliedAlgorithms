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

    void removeVertexW(int u){
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

    void deleteEdgeW(int u,int v){
        checkVertex(u);
        checkVertex(v);
        for(int i=adjLists[u].size()-1;i>=0;i--){
            Edge edge = adjLists[u].get(i);
            if(edge.to==v){
                adjLists[u].remove(i);
            }
        }
    }
}

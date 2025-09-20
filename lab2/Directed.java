public class Directed extends Graph{
    public Directed(int n){
        super(n);
    }

    void addEdge(int u, int v){
        checkVertex(u);
        checkVertex(v);
        adjLists[u].add(v);
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
    }
    }


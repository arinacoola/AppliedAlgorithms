public class Directed extends Graph<Integer>{
    public Directed(int n){
        super(n);
    }

    public void addEdge(int u, int v){
        checkVertex(u);
        checkVertex(v);
        adjLists[u].add(v);
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
    }
    }


import java.util.LinkedList;

public abstract class Graph  {
    protected int n;
    protected LinkedList<Integer> adjLists[];

    public Graph(int  vertices ){
        n= vertices;
        adjLists=new LinkedList[vertices];
        for (int i = 0; i < n; i++) {
            adjLists[i] = new LinkedList<>();
        }

    }

    void addVertex(){
       n=n+1;
       LinkedList<Integer> adjNewLists[] = new LinkedList[n];
       System.arraycopy(adjLists, 0, adjNewLists, 0, n-1);
       adjNewLists[n-1] = new LinkedList<>();
       adjLists = adjNewLists;
    }
}



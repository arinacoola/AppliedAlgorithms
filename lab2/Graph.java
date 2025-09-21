import java.util.LinkedList;
import java.util.Random;

public abstract class Graph<R> {
    protected int n;
    protected LinkedList<R> adjLists[];

    public Graph(int vertices) {
        n = vertices;
        adjLists = new LinkedList[vertices];
        for (int i = 0; i < n; i++) {
            adjLists[i] = new LinkedList<>();
        }

    }

    public void addVertex() {
        n = n + 1;
        LinkedList<R> adjNewLists[] = new LinkedList[n];
        System.arraycopy(adjLists, 0, adjNewLists, 0, n - 1);
        adjNewLists[n - 1] = new LinkedList<>();
        adjLists = adjNewLists;
    }

    protected void checkVertex(int u) {
        if (u < 0 || u >= n) {
            throw new IndexOutOfBoundsException("The vertex does not exist: " + u);
        }
    }

    public void deleteVertex(int u) {
        checkVertex(u);
        for (int i = 0; i < n; i++) {
            if (i == u) continue;
            for (int j = adjLists[i].size() - 1; j >= 0; j--) {
                int neighbor = (int) adjLists[i].get(j);
                if (neighbor == u) {
                    adjLists[i].remove(j);
                } else if (neighbor > u) {
                    adjLists[i].set(j, (R)(Integer)(neighbor - 1));
                }
            }
        }
        for (int i = u; i < n - 1; i++) {
            adjLists[i] = adjLists[i + 1];
        }
        adjLists[n - 1] = null;
        n--;
    }

    public  int[][] convertToAdjMatrix() {
        int[][] adjMatrix = new int[n][n];
        for (int i =0;i<n;i++){
            for( R el:adjLists[i]){
                int j = (Integer) el;
                   adjMatrix[i][j]=1;

            }
        }
       return adjMatrix;
    }

    public void BackAdjMatrix(int [][] adjMatrix){
        for (int i = 0; i < n; i++) {
            adjLists[i] = new LinkedList<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adjMatrix[i][j] != 0) {
                    addEdge(i, j);
                }
            }
        }



    }

    public abstract void addEdge(int u, int v);

    public void erdosRenyi(int n, float p) {
        this.n = n;
        adjLists = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            adjLists[i] = new LinkedList<>();
        }
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (random.nextFloat() < p)
                    addEdge(i, j);
            }
        }

    }





}



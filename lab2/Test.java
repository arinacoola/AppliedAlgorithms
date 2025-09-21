import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        testUndirected();
        testDirected();
    }

    public static void printGraph(Graph<Integer> g) {
        System.out.println("Graph with " + g.n + " vertices:");
        for (int i = 0; i < g.n; i++) {
            System.out.print(i + ": ");
            for (Object neighbor : g.adjLists[i]) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printWeightedGraph(Graph<Edge> g) {
        System.out.println("Weighted graph with " + g.n + " vertices:");
        for (int i = 0; i < g.n; i++) {
            System.out.print(i + ": ");
            for (Edge e : g.adjLists[i]) {
                System.out.print("(" + e.to + ", w=" + e.weight + ") ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void testUndirected() {
        System.out.println("Testing Undirected Graph");
        Undirected g = new Undirected(3);
        g.addEdge(0,1);
        g.addEdge(1,2);
        printGraph(g);

        g.deleteEdge(1,2);
        printGraph(g);

        g.addVertex();
        g.addEdge(2,3);
        printGraph(g);

        g.deleteVertex(1);
        printGraph(g);

        int[][] matrix = g.convertToAdjMatrix();
        System.out.println("Adjacency Matrix: ");
        for (int[] row : matrix){
            System.out.println(Arrays.toString(row));
        }
        System.out.println();

        g.BackAdjMatrix(matrix);
        printGraph(g);
    }

    public static void testDirected() {
        System.out.println("Testing Directed Graph");
        Directed g = new Directed(3);
        g.addEdge(0,1);
        g.addEdge(1,2);
        printGraph(g);

        g.deleteEdge(0,1);
        printGraph(g);

        g.addVertex();
        g.addEdge(2,3);
        printGraph(g);

        g.deleteVertex(1);
        printGraph(g);

        int[][] matrix = g.convertToAdjMatrix();
        System.out.println("Adjacency Matrix: ");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();

        g.BackAdjMatrix(matrix);
        printGraph(g);
    }

}


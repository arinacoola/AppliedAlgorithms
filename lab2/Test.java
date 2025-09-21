import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        testUndirected();
        testDirected();
        testWeightedUndirected();
        testWeightedDirected();
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

    public static void testWeightedUndirected() {
        System.out.println("Testing Weighted Undirected Graph");
        WeightedUndirected g = new WeightedUndirected(3);
        g.addEdge(0,1,5);
        g.addEdge(1,2,10);
        printWeightedGraph(g);

        g.deleteEdge(0,1);
        printWeightedGraph(g);

        g.addVertex();
        g.addEdge(2,3,7);
        printWeightedGraph(g);

        g.deleteVertex(1);
        printWeightedGraph(g);

        int[][] matrix = g.convertToAdjMatrixW();
        System.out.println("Weighted Adjacency Matrix: ");
        for (int[] row : matrix) System.out.println(Arrays.toString(row));
        System.out.println();

        g.BackAdjMatrixW(matrix);
        printWeightedGraph(g);
    }

    public static void testWeightedDirected() {
        System.out.println("Testing Weighted Directed Graph");
        WeightedDirected g = new WeightedDirected(3);
        g.addEdge(0,1,3);
        g.addEdge(1,2,8);
        printWeightedGraph(g);

        g.deleteEdge(0,1);
        printWeightedGraph(g);

        g.addVertex();
        g.addEdge(2,3,6);
        printWeightedGraph(g);

        g.deleteVertex(1);
        printWeightedGraph(g);

        int[][] matrix = g.convertToAdjMatrixW();
        System.out.println("Weighted Adjacency Matrix: ");
        for (int[] row : matrix) System.out.println(Arrays.toString(row));
        System.out.println();

        g.BackAdjMatrixW(matrix);
        printWeightedGraph(g);
    }

}


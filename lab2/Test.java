import javax.swing.*;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        testUndirected();
        testDirected();
        testWeightedUndirected();
        testWeightedDirected();

        WeightedDirected g = new WeightedDirected(4);
        g.addEdge(0, 1, 5);
        g.addEdge(1, 2, 3);
        g.addEdge(2, 3, 7);

        try {
            PythonGraphClient.sendGraphAndShowImage(g.exportEdgesAsJson(), true, true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error sending graph " + e.getMessage());
        }

    }

    public static void printGraph(Graph<Integer> g) {
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
        System.out.println("Adding edge 0-1 and 1-2: ");
        g.addEdge(0,1);
        g.addEdge(1,2);
        printGraph(g);
        System.out.println("Deleting edge 1-2: ");
        g.deleteEdge(1,2);
        printGraph(g);
        System.out.println("Adding a new vertex: ");
        g.addVertex();
        System.out.println("Adding edge 2-3: ");
        g.addEdge(2,3);
        printGraph(g);
        System.out.println("Deleting vertex 1: ");
        g.deleteVertex(1);
        printGraph(g);
        System.out.println("Converting to adjacency matrix: ");
        int[][] matrix = g.convertToAdjMatrix();
        for (int[] row : matrix){
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
        System.out.println("Recovering a graph from an adjacency matrix: ");
        g.BackAdjMatrix(matrix);
        printGraph(g);
    }

    public static void testDirected() {
        System.out.println("Testing Directed Graph");
        Directed g = new Directed(3);
        System.out.println("Adding edges 0->1 and 1->2: ");
        g.addEdge(0,1);
        g.addEdge(1,2);
        printGraph(g);
        System.out.println("Deleting edge 0->1: ");
        g.deleteEdge(0,1);
        printGraph(g);
        System.out.println("Adding a new vertex: ");
        g.addVertex();
        System.out.println("Adding edge 2->3: ");
        g.addEdge(2,3);
        printGraph(g);
        System.out.println("Deleting vertex 1: ");
        g.deleteVertex(1);
        printGraph(g);
        System.out.println("Converting to adjacency matrix: ");
        int[][] matrix = g.convertToAdjMatrix();
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
        System.out.println("Recovering a graph from an adjacency matrix: ");
        g.BackAdjMatrix(matrix);
        printGraph(g);
    }

    public static void testWeightedUndirected() {
        System.out.println("Testing Weighted Undirected Graph");
        WeightedUndirected g = new WeightedUndirected(3);
        System.out.println("Adding edges 0-1 (w=5) and 1-2 (w=10): ");
        g.addEdge(0,1,5);
        g.addEdge(1,2,10);
        printWeightedGraph(g);
        System.out.println("Deleting edge 0-1: ");
        g.deleteEdge(0,1);
        printWeightedGraph(g);
        System.out.println("Adding a new vertex: ");
        g.addVertex();
        System.out.println("Adding edge 2-3 (w=7): ");
        g.addEdge(2,3,7);
        printWeightedGraph(g);
        System.out.println("Deleting vertex 1: ");
        g.deleteVertex(1);
        printWeightedGraph(g);
        System.out.println("Converting to weighted adjacency matrix: ");
        int[][] matrix = g.convertToAdjMatrixW();
        for (int[] row : matrix) System.out.println(Arrays.toString(row));
        System.out.println();
        System.out.println("Recovering a graph from an adjacency matrix: ");
        g.BackAdjMatrixW(matrix);
        printWeightedGraph(g);
    }

    public static void testWeightedDirected() {
        System.out.println("Testing Weighted Directed Graph");
        WeightedDirected g = new WeightedDirected(3);
        System.out.println("Adding edges 0->1 (w=3) and 1->2 (w=8): ");
        g.addEdge(0,1,3);
        g.addEdge(1,2,8);
        printWeightedGraph(g);
        System.out.println("Deleting edge 0->1: ");
        g.deleteEdge(0,1);
        printWeightedGraph(g);
        System.out.println("Adding a new vertex: ");
        g.addVertex();
        System.out.println("Adding edge 2->3 (w=6): ");
        g.addEdge(2,3,6);
        printWeightedGraph(g);
        System.out.println("Deleting vertex 1: ");
        g.deleteVertex(1);
        printWeightedGraph(g);
        System.out.println("Converting to weighted adjacency matrix: ");
        int[][] matrix = g.convertToAdjMatrixW();
        for (int[] row : matrix) System.out.println(Arrays.toString(row));
        System.out.println();
        System.out.println("Recovering a graph from an adjacency matrix: ");
        g.BackAdjMatrixW(matrix);
        printWeightedGraph(g);
    }


}


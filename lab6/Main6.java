import java.util.Arrays;

public class Main6 {

    public static void main(String[] args) {

        System.out.println("Comparison of different MergeSort variants: ");

        Sorter[] algList = {
                new TopDownMergeSort(),
                new BottomUpMergeSort(),
                new OptimizedMergeSort(),
                new TenWayMergeSort()
        };

        String[] dataNames = {
                "Random",
                "Sorted",
                "Reversed",
                "Almost sorted",
                "Few unique"
        };

        int[] testSizes = {100, 1000, 5000, 20000};

        for (int n : testSizes) {
            System.out.println("\n-------------------------------------");
            System.out.println("Array size: " + n);
            System.out.println("-------------------------------------");

            for (int t = 1; t <= 5; t++) {

                System.out.println("\nData type: " + dataNames[t - 1]);

                int[] base = createData(n, t);

                for (Sorter alg : algList) {

                    int[] arr = Arrays.copyOf(base, base.length);
                    Metrics m = new Metrics();

                    alg.sort(arr, m);
                    System.out.println(
                            alg.nameAlg()
                                    + " | comp=" + m.comparison
                                    + " | cop=" + m.cop
                                    + " | time=" + String.format("%.3f", m.getTime()) + "ms"
                                    + " | mem=" + m.extraMemory
                    );
                }
            }
        }
    }

    private static int[] createData(int n, int type) {
        switch (type) {
            case 1:
                return DataGenerator.randArray(n, 0, 1_000_000);
            case 2:
                return DataGenerator.sortedArray(n);
            case 3:
                return DataGenerator.reverseSortedArray(n);
            case 4:
                return DataGenerator.almostSortedArray(n);
            case 5:
                return DataGenerator.fewUniqueArray(n, 5);
        }
        return new int[0];
    }
}

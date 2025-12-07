public class Main7{
    public static void main(String[] args) {
        int[] sizes = {1000, 5000, 10000, 20000};
        SorterConfig[] configs = {
                new SorterConfig("Lomuto LAST", new LomutoSorter(SelectionPivot.LAST)),
                new SorterConfig("Lomuto RAND", new LomutoSorter(SelectionPivot.RANDOM)),
                new SorterConfig("Lomuto MED3", new LomutoSorter(SelectionPivot.MEDIAN3)),
                new SorterConfig("Lomuto MED3RND", new LomutoSorter(SelectionPivot.MEDIAN3RND)),

                new SorterConfig("Hoare LAST", new HoareSorter(SelectionPivot.LAST)),
                new SorterConfig("Hoare RAND", new HoareSorter(SelectionPivot.RANDOM)),
                new SorterConfig("Hoare MED3", new HoareSorter(SelectionPivot.MEDIAN3)),
                new SorterConfig("Hoare MED3RND", new HoareSorter(SelectionPivot.MEDIAN3RND)),

                new SorterConfig("ThreeWay", new ThreeWaySorter()),
                new SorterConfig("DualPivot", new DualPivotSorter())
        };
        String[] dataNames = {
                "CONST", "SORTED", "RANDOM", "ALMOST", "REVERSED", "TRIANGLE", "FEWDIFF"
        };
        System.out.printf("%-15s %-10s %-8s %-10s %-12s %-12s %-12s\n",
                "Algorithm", "Data", "N", "Time(ms)", "Comp", "Swaps", "Mem");
        for (int n : sizes) {
            int[][] datasets = {
                    DataGenerator7.constArr(n, 5), DataGenerator7.sortArr(n), DataGenerator7.rndArr(n), DataGenerator7.almostSortArr(n), DataGenerator7.reversArr(n), DataGenerator7.triangleArr(n), DataGenerator7.fewDifferentArr(n)
            };
            for (int d = 0; d < datasets.length; d++) {
                for (SorterConfig cfg : configs) {
                    int[] arr = DataGenerator7.copy(datasets[d]);
                    Metrics7 m = new Metrics7();

                    Runtime rt = Runtime.getRuntime();
                    rt.gc();
                    long memBefore = rt.totalMemory() - rt.freeMemory();

                    long start = System.nanoTime();
                    cfg.sorter.sort(arr, m);
                    long end = System.nanoTime();

                    long memAfter = rt.totalMemory() - rt.freeMemory();
                    long memUsed = memAfter - memBefore;
                    double timeMs = (end - start) / 1_000_000.0;

                    System.out.printf("%-15s %-10s %-8d %-10d %-12d %-12d %-12d\n",
                            cfg.name, dataNames[d], n, timeMs, m.comp, m.swaps, memUsed);
                }
            }
        }
    }
}

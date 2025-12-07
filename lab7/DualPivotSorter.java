public  class DualPivotSorter implements Sorter7 {
    public void sort(int[] a, Metrics7 m) {
        new DualPivot().quickSort(a, 0, a.length - 1, m);
    }
}

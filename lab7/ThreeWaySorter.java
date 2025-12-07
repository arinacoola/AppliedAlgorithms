public  class ThreeWaySorter implements Sorter7 {
    public void sort(int[] a, Metrics7 m) {
        new QuickSort3Way().quickSort(a, 0, a.length - 1, m);
    }
}

public  class HoareSorter implements Sorter7 {
    private final SelectionPivot pivot;
    public HoareSorter(SelectionPivot pivot) {
        this.pivot = pivot;
    }

    public void sort(int[] a, Metrics7 m) {
        new Hoare().quickSort(a, 0, a.length - 1, m, pivot);
    }
}


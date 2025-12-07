public class LomutoSorter implements Sorter7 {
    private final SelectionPivot pivot;
    public LomutoSorter(SelectionPivot pivot) {
        this.pivot = pivot;
    }

    public void sort(int[] a, Metrics7 m) {
        new Lomuto().quickSort(a, 0, a.length - 1, m, pivot);
    }
}


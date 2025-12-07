public class Lomuto {
    public int partition(int[] a, int left, int right, Metrics7 m) {
        int pivot = a[right];
        int i = left;
        for (int j = left; j < right; j++) {
            m.comp++;
            if (a[j] <= pivot) {
                Helper.swap(a, i, j, m);
                i++;
            }
        }
        Helper.swap(a, i, right, m);
        return i;
    }

    public void quickSort(int[] a, int left, int right, Metrics7 m, SelectionPivot type) {
        if (left >= right){
            return;
        }
        choosePivot(a, left, right, m, type);
        int p = partition(a, left, right, m);
        quickSort(a, left, p - 1, m, type);
        quickSort(a, p + 1, right, m, type);
    }

    private void choosePivot(int[] a, int left, int right, Metrics7 m, SelectionPivot type) {
        if (type == SelectionPivot.LAST) {
            return;
        }

        if (type == SelectionPivot.RANDOM) {
            int rnd = Helper.randomInd(left, right);
            Helper.swap(a, rnd, right, m);
            return;
        }

        if (type == SelectionPivot.MEDIAN3) {
            int mid = (left + right) / 2;
            int med = Helper.medianThree(a, left, mid, right, m);
            Helper.swap(a, med, right, m);
            return;
        }

        int medRnd = Helper.medianThreeRnd(a, left, right, m);
        Helper.swap(a, medRnd, right, m);
    }
}

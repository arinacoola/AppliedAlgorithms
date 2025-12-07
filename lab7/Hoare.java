public class Hoare {
    public int partition(int[] a, int left, int right, Metrics7 m) {
        int pivot = a[left];
        int i = left;
        int j = right;

        while (true) {
            i++;
            m.comp++;
            while (i <= right && a[i] < pivot) {
                i++;
                m.comp++;
            }
            j--;
            m.comp++;
            while (j >= left && a[j] > pivot) {
                j--;
                m.comp++;
            }
            if (i >= j){
                return j;
            }
            Helper.swap(a, i, j, m);
        }
    }

    public void quickSort(int[] a, int left, int right, Metrics7 m, SelectionPivot type) {
        if (left >= right){
            return;
        }
        choosePivot(a, left, right, m, type);
        int p = partition(a, left, right, m);
        quickSort(a, left, p, m, type);
        quickSort(a, p + 1, right, m, type);
    }

    private void choosePivot(int[] a, int left, int right, Metrics7 m, SelectionPivot type) {
        if (type == SelectionPivot.LAST) {
            Helper.swap(a, left, right, m);
            return;
        }

        if (type == SelectionPivot.RANDOM) {
            int rnd = Helper.randomInd(left, right);
            Helper.swap(a, left, rnd, m);
            return;
        }

        if (type == SelectionPivot.MEDIAN3) {
            int mid = (left + right) / 2;
            int med = Helper.medianThree(a, left, mid, right, m);
            Helper.swap(a, left, med, m);
            return;
        }

        int medRnd = Helper.medianThreeRnd(a, left, right, m);
        Helper.swap(a, left, medRnd, m);
    }
}

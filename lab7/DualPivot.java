public class DualPivot {
    public void quickSort(int[] a, int left, int right, Metrics7 m) {
        if (left >= right) {
            return;
        }
        m.comp++;
        if (a[left] > a[right]) {
            Helper.swap(a, left, right, m);
        }
        int pivotOne = a[left];
        int pivotTwo = a[right];
        int low = left + 1;
        int high = right - 1;
        int i = left + 1;
        while (i <= high) {
            m.comp++;
            if (a[i] < pivotOne) {
                Helper.swap(a, i, low, m);
                low++;
                i++;
                continue;
            }
            m.comp++;
            if (a[i] > pivotTwo) {
                Helper.swap(a, i, high, m);
                high--;
                continue;
            }
            i++;
        }
        low--;
        high++;

        Helper.swap(a, left, low, m);
        Helper.swap(a, right, high, m);
        quickSort(a, left, low - 1, m);
        quickSort(a, low + 1, high - 1, m);
        quickSort(a, high + 1, right, m);
    }
}

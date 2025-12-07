public class QuickSort3Way{
    public void quickSort(int[] a, int left, int right, Metrics7 m) {
        if (left >= right){
            return;
        }
        int pivotInd = Helper.medianThreeRnd(a, left, right, m);
        Helper.swap(a, left, pivotInd, m);
        int pivot = a[left];
        int low = left;
        int high = right;
        int i = left + 1;

        while (i <= high) {
            m.comp++;
            if (a[i] < pivot) {
                Helper.swap(a, low, i, m);
                low++;
                i++;
            }
            else {
                m.comp++;
                if (a[i] > pivot) {
                    Helper.swap(a, i, high, m);
                    high--;
                }
                else {
                    i++;
                }
            }
        }
        quickSort(a, left, low - 1, m);
        quickSort(a, high + 1, right, m);
    }
}

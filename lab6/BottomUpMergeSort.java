public class BottomUpMergeSort implements Sorter {
    @Override
    public String nameAlg() {
        return "Bottom-Up MergeSort";
    }
    @Override
    public void sort(int[] a, Metrics metrics) {
        metrics.reset();
        int n = a.length;

        long start = System.nanoTime();

        int[] aux = new int[n];
        metrics.extraMemory = (long) n * Integer.BYTES;

        for (int size = 1; size < n; size *= 2) {
            for (int left = 0; left + size < n; left += size * 2) {
                int mid = left + size - 1;
                int right = Math.min(left + size * 2 - 1, n - 1);

                merge(a, aux, left, mid, right, metrics);
            }
        }

        metrics.time = System.nanoTime() - start;
    }

    private void merge(int[] a, int[] aux, int left, int mid, int right, Metrics m) {
        for (int k = left; k <= right; k++) {
            aux[k] = a[k];
            m.cop++;
        }

        int i = left;
        int j = mid + 1;

        for (int k = left; k <= right; k++) {

            if (i > mid) {
                a[k] = aux[j++];
                m.cop++;
            }
            else if (j > right) {
                a[k] = aux[i++];
                m.cop++;
            }
            else {
                m.comparison++;
                if (aux[j] < aux[i]) {
                    a[k] = aux[j++];
                } else {
                    a[k] = aux[i++];
                }
                m.cop++;
            }
        }
    }
}

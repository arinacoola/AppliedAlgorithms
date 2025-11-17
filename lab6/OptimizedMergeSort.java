public class OptimizedMergeSort implements Sorter {
    private static final int CUTOFF = 12;
    @Override
    public String nameAlg() {
        return "Optimized MergeSort (cutoff + stop-if-already-sorted + eliminate-the-copy-to-aux-array)";
    }

    @Override
    public void sort(int[] a, Metrics metrics) {
        metrics.reset();
        int n = a.length;
        long start = System.nanoTime();

        int[] aux = a.clone();
        metrics.extraMemory = (long) n * Integer.BYTES;
        optimizedSort(aux, a, 0, n - 1, metrics);

        metrics.time = System.nanoTime() - start;

    }
    private void optimizedSort(int[] src, int[] dst, int left, int right, Metrics m) {
        if (right - left <= CUTOFF) {
            insertionSort(dst, left, right, m);
            return;
        }
        int mid = left + (right - left) / 2;
        optimizedSort(dst, src, left, mid, m);
        optimizedSort(dst, src, mid + 1, right, m);
        m.comparison++;
        if (src[mid] <= src[mid + 1]) {
            System.arraycopy(src, left, dst, left, right - left + 1);
            m.cop += (right - left + 1);
            return;
        }
        merge(src, dst, left, mid, right, m);
    }

    private void insertionSort(int[] a, int lo, int hi, Metrics m) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = a[i];
            m.cop++;

            int j = i - 1;
            while (j >= lo) {
                m.comparison++;
                if (a[j] > key) {
                    a[j + 1] = a[j];
                    m.cop++;
                    j--;
                } else {
                    break;
                }
            }
            a[j + 1] = key;
            m.cop++;
        }
    }

    private void merge(int[] src, int[] dst, int left, int mid, int right, Metrics m) {
        int i = left;
        int j = mid + 1;

        for (int k = left; k <= right; k++) {
            if (i > mid) {
                dst[k] = src[j++];
                m.cop++;
            }
            else if (j > right) {
                dst[k] = src[i++];
                m.cop++;
            }
            else {
                m.comparison++;
                if (src[j] < src[i]) {
                    dst[k] = src[j++];
                } else {
                    dst[k] = src[i++];
                }
                m.cop++;
            }
        }
    }
}


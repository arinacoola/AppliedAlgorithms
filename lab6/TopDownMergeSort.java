import java.util.Arrays;
public class TopDownMergeSort implements Sorter {
    @Override
    public String nameAlg() {
        return "Top-Down MergeSort";
    }

    @Override
    public void sort(int[] n, Metrics metrics) {
        if (n == null || n.length <= 1) {
            return;
        }
        metrics.reset();
        long start = System.nanoTime();

        int[] aux = new int[n.length];
        metrics.extraMemory = (long) aux.length * Integer.BYTES;

        sortRec(n, aux, 0, n.length - 1, metrics);

        metrics.time = System.nanoTime() - start;
    }

    private void sortRec(int[] n, int[] aux, int left, int right, Metrics m) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;

        sortRec(n, aux, left, mid, m);
        sortRec(n, aux, mid + 1, right, m);

        merge(n, aux, left, mid, right, m);
    }

    private void merge(int[] n, int[] aux, int left, int mid, int right, Metrics m) {
        for (int k = left; k <= right; k++) {
            aux[k] = n[k];
            m.cop++;
        }

        int i = left;
        int j = mid + 1;

        for (int k = left; k <= right; k++) {
            if (i > mid) {
                n[k] = aux[j++];
                m.cop++;
            } else if (j > right) {
                n[k] = aux[i++];
                m.cop++;
            } else {
                m.comparison++;
                if (aux[j] < aux[i]) {
                    n[k] = aux[j++];
                } else {
                    n[k] = aux[i++];
                }
                m.cop++;
            }
        }
    }
}


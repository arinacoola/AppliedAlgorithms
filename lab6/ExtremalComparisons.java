import java.util.Arrays;
public class ExtremalComparisons {
    private static final int MAX_N = 10;
    private static final Sorter sorter = new TopDownMergeSort();
    private static final Metrics metrics = new Metrics();

    public static void main(String[] args) {
        System.out.println("Additional task (a): \n");

        for (int n = 1; n <= MAX_N; n++) {
            long shifts = factorial(n);
            if (shifts > 5_000_000) {
                System.out.println("n = " + n + " -> " + shifts +
                        " We will not calculate this value n, because it will take too much time.");
                break;
            }

            if (n == 1) {
                System.out.println("n = 1: min = 0, max = 0, perm = [0]");
                continue;
            }

            int[] base = new int[n];
            for (int i = 0; i < n; i++) base[i] = i;

            Result res = new Result();
            res.minComparisons = Long.MAX_VALUE;
            res.maxComparisons = Long.MIN_VALUE;

            long start = System.currentTimeMillis();
            generateAll(base, 0, res);
            long end = System.currentTimeMillis();

            System.out.println("n = " + n);
            System.out.println("shifts: " + shifts);
            System.out.println("sorting time:  " + (end - start) + " ms");
            System.out.println("min comparisons = " + res.minComparisons +
                    ", perm = " + Arrays.toString(res.minPerm));
            System.out.println("max comparisons = " + res.maxComparisons +
                    ", perm = " + Arrays.toString(res.maxPerm));
            System.out.println();
        }
    }

    private static void generateAll(int[] a, int pos, Result res) {
        if (pos == a.length) {

            int[] arr = Arrays.copyOf(a, a.length);
            metrics.reset();
            sorter.sort(arr, metrics);
            long cmp = metrics.comparison;

            if (cmp < res.minComparisons) {
                res.minComparisons = cmp;
                res.minPerm = Arrays.copyOf(a, a.length);
            }
            if (cmp > res.maxComparisons) {
                res.maxComparisons = cmp;
                res.maxPerm = Arrays.copyOf(a, a.length);
            }

            return;
        }

        for (int i = pos; i < a.length; i++) {
            swap(a, pos, i);
            generateAll(a, pos + 1, res);
            swap(a, pos, i);
        }
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static long factorial(int n) {
        long r = 1;
        for (int i = 2; i <= n; i++) r *= i;
        return r;
    }

    private static class Result {
        long minComparisons;
        long maxComparisons;
        int[] minPerm;
        int[] maxPerm;
    }
}


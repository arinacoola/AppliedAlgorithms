import java.util.Random;
import java.util.Arrays;

public class DataGenerator {
    private static final Random rnd = new Random();

    public static int[] randArray(int n, int min, int maxExclusive) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = rnd.nextInt(maxExclusive - min) + min;
        }
        return a;
    }

    public static int[] sortedArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = i;
        return a;
    }

    public static int[] reverseSortedArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = n - i;
        return a;
    }

    public static int[] almostSortedArray(int n) {
        int[] a = sortedArray(n);

        int swaps = Math.max(1, n / 20); // 5%
        for (int i = 0; i < swaps; i++) {
            int idx1 = rnd.nextInt(n);
            int idx2 = rnd.nextInt(n);
            int tmp = a[idx1];
            a[idx1] = a[idx2];
            a[idx2] = tmp;
        }
        return a;
    }

    public static int[] fewUniqueArray(int n, int uniqueCount) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = rnd.nextInt(uniqueCount);
        }
        return a;
    }

    public static int[] copyOf(int[] a) {
        return Arrays.copyOf(a, a.length);
    }
}


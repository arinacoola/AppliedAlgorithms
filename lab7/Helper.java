import java.util.Random;

public class Helper {
    private static final Random rnd = new Random();
    public static void swap(int[] a, int i, int j, Metrics7 m) {
        if (i == j) return;
        m.swaps++;
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static int randomInd(int left, int right) {
        return left + rnd.nextInt(right - left + 1);
    }

    public static int medianThree(int[] a, int i, int j, int k, Metrics7 m) {
        m.comp++;
        if (a[i] <= a[j]) {
            m.comp++;
            if (a[j] <= a[k]) {
                return j;
            }
            m.comp++;
            if (a[i] <= a[k]) {
                return k;
            }
            return i;
        }
        else {
            m.comp++;
            if (a[i] <= a[k]) {
                return i;
            }
            m.comp++;
            if (a[j] <= a[k]) {
                return k;
            }
            return j;
        }
    }

    public static int medianThreeRnd(int[] a, int left, int right, Metrics7 m) {
        int i = randomInd(left, right);
        int j = randomInd(left, right);
        int k = randomInd(left, right);
        return medianThree(a, i, j, k, m);
    }
}

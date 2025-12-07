import java.util.Random;

public class DataGenerator7 {
    private static final Random rnd = new Random();

    public static int[] constArr(int n, int value) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = value;
        }
        return a;
    }

    public static int[] sortArr(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
        return a;
    }

    public static int[] rndArr(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = rnd.nextInt();
        }
        return a;
    }

    public static int[] almostSortArr(int n) {
        int[] a = sortArr(n);
        int k = Math.max(1, n / 100);
        for (int t = 0; t < k; t++) {
            int i = rnd.nextInt(n);
            int val = a[i];
            for (int j = i; j < n - 1; j++) {
                a[j] = a[j + 1];
            }
            int newPos = rnd.nextInt(n);
            for (int j = n - 1; j > newPos; j--) {
                a[j] = a[j - 1];
            }
            a[newPos] = val;
        }
        return a;
    }

    public static int[] reversArr(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = n - i;
        }
        return a;
    }

    public static int[] triangleArr(int n) {
        int[] a = new int[n];
        int half = n / 2;
        for (int i = 0; i < half; i++) {
            a[i] = i;
        }
        for (int i = 0; i < half; i++){
            a[n - 1 - i] = i;
        }
        if (n % 2 == 1) {
            a[half] = half;
        }
        return a;
    }

    public static int[]  fewDifferentArr(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++){
            a[i] = rnd.nextInt(5);
        }
        return a;
    }

    public static int[] copy(int[] a) {
        int[] l = new int[a.length];
        System.arraycopy(a, 0, l, 0, a.length);
        return l;
    }
}

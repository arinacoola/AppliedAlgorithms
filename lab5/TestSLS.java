public class TestSLS {
    public static void main(String[] args) {
        double[][] dataA = {
                {2, 1, 1},
                {4, -6, 0},
                {-2, 7, 2}
        };
        double[] b = {5, -2, 9};

        Matrix A = new Matrix(dataA);
        LUP lup = new LUP(A);

        double[] x = lup.solve(b);

        System.out.println("Solution of the system:");
        for (int i = 0; i < x.length; i++) {
            System.out.printf("x%d = %.6f%n", i + 1, x[i]);
        }

        System.out.println("\nVerification: A·x = b");
        double[] check = A.multiplyVector(x);
        for (int i = 0; i < check.length; i++) {
            System.out.printf("b[%d] = %.6f (expected %.6f)%n", i + 1, check[i], b[i]);
        }
    }
}

public class Matrix {
    int n;
    double[][] el;

    public Matrix(int n) {
        this.n = n;
        this.el = new double[n][n];
    }

    public Matrix(double[][] newEl) {
        this.n = newEl.length;
        this.el = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.el[i][j] = newEl[i][j];
            }
        }
    }

    public double get(int i, int j) {
        if (i >= n || i < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (j >= n || j < 0) {
            throw new IndexOutOfBoundsException();
        }
        return el[i][j];
    }

    public void set(int i, int j, double val) {
        if (i >= n || i < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (j >= n || j < 0) {
            throw new IndexOutOfBoundsException();
        }
        el[i][j] = val;
    }

    public void swapRows(int i, int j) {
        if (i >= n || i < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (j >= n || j < 0) {
            throw new IndexOutOfBoundsException();
        }
        for(int k=0;k<n;k++){
            double temp=el[i][k];
            el[i][k]=el[j][k];
            el[j][k]=temp;
        }
    }

    public Matrix add(Matrix other){
        if (this.n != other.n) {
            throw new IllegalArgumentException();
        }
        Matrix C = new Matrix(n);
        for(int i=0;i<n;i++){
            double sum=0;
            for(int j=0;j<n;j++){
                double a,b;
                a=this.get(i, j);
                b=other.get(i, j);
                C.set(i,j,a + b);
            }

        }
        return C;
    }

    public Matrix sub(Matrix other){
        if (this.n != other.n) {
            throw new IllegalArgumentException();
        }
        Matrix C = new Matrix(n);
        for(int i=0;i<n;i++){
            double diff=0;
            for(int j=0;j<n;j++){
                double a,b;
                a=this.get(i, j);
                b=other.get(i, j);
                C.set(i,j,a - b);
            }

        }
        return C;
    }


    public Matrix multiply(Matrix other) {
        if (this.n != other.n) {
            throw new ArithmeticException();
        }
        Matrix C = new Matrix(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double sum=0;
                for (int k = 0; k < n; k++) {
                    double a,b;
                    a=this.get(i, k);
                    b=other.get(k, j);
                    sum += a * b;
                }
                C.set(i,j,sum);
            }
        }
        return C;
    }

    public double[] multiplyVector(double[] vector){
        if(vector.length!=n){
            throw new IllegalArgumentException();
        }
        double[] result = new double[n];
        for(int i=0;i<n;i++){
            double sum=0;
            for(int j=0;j<n;j++){
                double a=this.get(i,j);
                sum += a * vector[j];
            }
            result[i] = sum;
        }
        return result;
    }

    public static Matrix identity(int n){
        Matrix I = new Matrix(n);
        for (int i=0;i<n;i++){
            I.set(i, i, 1.0);
        }
        return I;
    }

    public Matrix copy(){
        double[][] newEl = new double[n][n];
        for(int i=0;i<n;i++){
            for(int j=0; j<n;j++){
                newEl[i][j] = this.get(i, j);
            }
        }
        Matrix copy = new Matrix(newEl);
        return copy;

    }



}


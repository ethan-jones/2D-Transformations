public class Matrix {
    
    public int M;             // number of rows
    public int N;             // number of columns
    private double[][] data;   // M-by-N array

    // create M-by-N matrix of 0's
    public Matrix(int M, int N) {
        this.M = M;
        this.N = N;
        data = new double[M][N];
    }

    // create matrix based on 2d array
    public Matrix(double[][] data) {
        M = data.length;
        N = data[0].length;
        this.data = new double[M][N];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                    this.data[i][j] = data[i][j];
    }

    // copy constructor
    //private Matrix(Matrix A) { this(A.data); }

    // return C = A + B
    public Matrix plus(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = A.data[i][j] + B.data[i][j];
        return C;
    }


    // return C = A - B
    public Matrix minus(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = A.data[i][j] - B.data[i][j];
        return C;
    }

    // return C = A * B
    public Matrix times(Matrix B) {
        Matrix A = this;
        if (A.N != B.M) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(A.M, B.N);
        for (int i = 0; i < C.M; i++)
            for (int j = 0; j < C.N; j++)
                for (int k = 0; k < A.N; k++)
                    C.data[i][j] += (A.data[i][k] * B.data[k][j]);
        return C;
    }

    public void fillRows(int num) {
        for (int i = 0; i < M; i++) {
            data[i][0] = num;
        }
    }

    public void fillCols(int num) {
        for (int i = 0; i < N; i++) {
            data[0][i] = num;
        }
    }

    public double getValue(int row, int col) {
        return data[row][col];
    }

    public String toString() {
        String str = "[ ";
        
        for (int i = 0; i < M; i++) {
            if (i != 0) {
                str += "[ ";
            }
            for (int j = 0; j < N; j++) {
                str += data[i][j];
                if (j == N-1) {
                    str += " ]\n";
                } else {
                    str += ", ";
                }
            }
        }
        return str;
    }

}

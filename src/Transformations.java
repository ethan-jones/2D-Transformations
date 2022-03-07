public final class Transformations {

    private Transformations(){}

    public static int inputLines() {
        return 0;
    }

    public static Matrix basicTranslate(Matrix matrix, int x, int y) {

        Matrix transformation = new Matrix(matrix.M, matrix.N);
        transformation.fillRows(x);
        transformation.fillCols(y);

        return matrix.plus(transformation);
    }

    public static Matrix basicScale() {
        return new Matrix(1,1);
    }

    public static Matrix basicRotate() {
        return new Matrix(1,1);
    }

    public static Matrix scale() {
        return new Matrix(1,1);
    }

    public static Matrix rotate() {
        return new Matrix(1,1);
    }

}

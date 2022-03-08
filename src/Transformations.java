public final class Transformations {

    private Transformations(){}

    public static int inputLines() {
        return 0;
    }

    public static Matrix basicTranslate(Matrix matrix, int x, int y) {

        double[][] translation = {{1, 0, 0},
                               {0, 1, 0},
                               {x, y, 1}}; 
        double [][] temp = {{0, 0, 1}};
        Matrix coordinate;
        Matrix transformation = new Matrix(translation);
        for (int i = 0; i < matrix.N; i++) {
            temp[0][0] = matrix.getValue(0, i);
            temp[0][1] = matrix.getValue(1, i);
            coordinate = new Matrix(temp);
            coordinate = coordinate.times(transformation);
            matrix.data[0][i] = coordinate.getValue(0, 0);
            matrix.data[1][i] = coordinate.getValue(0, 1);
        }
        return matrix;
    }

    public static Matrix basicScale(Matrix matrix, double x, double y) {
        double [][] temp = {{0, 0, 1}};
        Matrix coordinate;
        double[][] translation = {{x, 0, 0},
                               {0, y, 0},
                               {0, 0, 1}};
        Matrix transformation = new Matrix(translation);
        for (int i = 0; i < matrix.N; i++) {
            temp[0][0] = matrix.getValue(0, i);
            temp[0][1] = matrix.getValue(1, i);
            coordinate = new Matrix(temp);
            coordinate = coordinate.times(transformation);
            matrix.data[0][i] = coordinate.getValue(0, 0);
            matrix.data[1][i] = coordinate.getValue(0, 1);
        }
        return matrix;
    }

    public static Matrix basicRotate(Matrix matrix, double degrees) {
        return rotate(matrix, degrees, 400, 400);
    }

    public static Matrix scale(Matrix matrix, int Sx, int Sy, int Cx, int Cy) {
        Matrix coordinate;
        double[][] temp = {{0, 0, 1}};
        double[][] translate = {{1, 0, 0},
                                {0, 1, 0},
                                {(-1)*Cx, (-1)*Cy, 1}};
        Matrix stepOne = new Matrix(translate);
        double[][] scale = {{Sx, 0, 0},
                            {0, Sy, 0},
                            {0, 0, 1}};
        Matrix stepTwo = new Matrix(scale);
        double[][] translateTwo = {{1, 0, 0},
                                   {0, 1, 0},
                                   {Cx, Cy, 1}};
        Matrix stepThree = new Matrix(translateTwo);
        for (int i = 0; i < matrix.N; i++) {
            temp[0][0] = matrix.getValue(0, i);
            temp[0][1] = matrix.getValue(1, i);
            coordinate = new Matrix(temp);
            coordinate = coordinate.times(stepOne).times(stepTwo).times(stepThree);
            matrix.data[0][i] = coordinate.getValue(0, 0);
            matrix.data[1][i] = coordinate.getValue(0, 1);
        }
        
        return matrix;
    }

    public static Matrix rotate(Matrix matrix, double degrees, int Cx, int Cy) {
        Matrix coordinate;
        double[][] temp = {{0, 0, 1}};
        double[][] translate = {{1, 0, 0},
                                {0, 1, 0},
                                {(-1)*Cx, (-1)*Cy, 1}};
        Matrix stepOne = new Matrix(translate);
        double radian = Math.toRadians(degrees);
        double[][] rotate = {{Math.cos(radian), (-1) * Math.sin(radian), 0},
                                  {Math.sin(radian), Math.cos(radian), 0},
                                  {0, 0, 1}};
        Matrix stepTwo = new Matrix(rotate);
        double[][] translateTwo = {{1, 0, 0},
                                   {0, 1, 0},
                                   {Cx, Cy, 1}};
        Matrix stepThree = new Matrix(translateTwo);
        for (int i = 0; i < matrix.N; i++) {
            temp[0][0] = matrix.getValue(0, i);
            temp[0][1] = matrix.getValue(1, i);
            coordinate = new Matrix(temp);
            coordinate = coordinate.times(stepOne).times(stepTwo).times(stepThree);
            matrix.data[0][i] = coordinate.getValue(0, 0);
            matrix.data[1][i] = coordinate.getValue(0, 1);
        }
        return matrix;
    }

}

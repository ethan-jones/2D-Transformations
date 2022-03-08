import java.math.*;
import java.util.Random;

public final class Transformations {

    private Transformations(){}

    public static int inputLines() {
        return 0;
    }

    public static Matrix basicTranslate(Matrix matrix, int x, int y) {

        double[][] translation = {{1, 0, 0},
                               {0, 1, 0},
                               {x, y, 1}}; 
        Matrix transformation = new Matrix(translation);
        double[][] temp = {{matrix.getValue(0, 0), matrix.getValue(1, 0), 1}};
        Matrix coordinate = new Matrix(temp);
        return coordinate.times(transformation);
    }

    public static Matrix basicScale(Matrix matrix, int x, int y) {
        
        double[][] translation = {{x, 0, 0},
                               {0, y, 0},
                               {0, 0, 1}};
        Matrix transformation = new Matrix(translation);
        double[][] temp = {{matrix.getValue(0, 0), matrix.getValue(1, 0), 1}};
        Matrix coordinate = new Matrix(temp);
        return coordinate.times(transformation);
    }

    public static Matrix basicRotate(Matrix matrix, double degrees) {
        double radian = Math.toRadians(degrees);
        double[][] translation = {{Math.cos(radian), (-1) * Math.sin(radian), 0},
                                  {Math.sin(radian), Math.cos(radian), 0},
                                  {0, 0, 1}};
        Matrix transformation = new Matrix(translation);
        double[][] temp = {{matrix.getValue(0, 0), matrix.getValue(1, 0), 1}};
        Matrix coordinate = new Matrix(temp);
        return coordinate.times(transformation);
    }

    public static Matrix scale(Matrix matrix, int Sx, int Sy, int Cx, int Cy) {
        
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
        double[][] temp = {{matrix.getValue(0, 0), matrix.getValue(1, 0), 1}};
        Matrix coordinate = new Matrix(temp);
        
        return coordinate.times(stepOne).times(stepTwo).times(stepThree);
    }

    public static Matrix rotate() {
        return new Matrix(1,1);
    }

}

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Scanner;
import java.io.*;

public class Graph extends JPanel {
    
    private BufferedImage canvas;
    public int numLines;

    public Graph(int width, int height) {

        Scanner reader = new Scanner(System.in);
        double Sx;
        double Sy;
        int Cx;
        int Cy;
        double degrees;
        System.out.println("Please type a valid path for the line input file.");
        String inputLines = reader.nextLine();
        System.out.println("Please type a vaild path for the line output file.");
        String outputLines = reader.nextLine();
        Matrix matrix = inputLines(inputLines);

        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        drawLine(Color.BLACK, 400, 0, 400, 799);
        drawLine(Color.BLACK, 0, 400, 799, 400);
        drawMatrix(Color.RED, matrix);

        System.out.println("Would you like to use translation, basic scale, basic rotate, scale, or rotate? (1, 2, 3, 4, 5)");
        int translation = reader.nextInt();
        if (translation == 1) {
            System.out.println("Please enter the x displacement.");
            Cx = reader.nextInt();
            System.out.println("Please enter the y displacement.");
            Cy = reader.nextInt();
            matrix = Transformations.basicTranslate(matrix, Cx, Cy);
            drawMatrix(Color.BLUE, matrix);
            outputLines(matrix, numLines, outputLines);
        } else if (translation == 2) {
            System.out.println("Please enter the x scaling factor.");
            Sx = reader.nextDouble();
            System.out.println("Please enter the y scaling factor.");
            Sy = reader.nextDouble();
            matrix = Transformations.basicScale(matrix, Sx, Sy);
            drawMatrix(Color.BLUE, matrix);
            outputLines(matrix, numLines, outputLines);
        } else if (translation == 3) {
            System.out.println("Please enter the angle.");
            degrees = reader.nextDouble();
            matrix = Transformations.basicRotate(matrix, degrees);
            drawMatrix(Color.BLUE, matrix);
            outputLines(matrix, numLines, outputLines);
        } else if (translation == 4) {
            System.out.println("Please enter the x scaling factor.");
            Sx = reader.nextDouble();
            System.out.println("Please enter the y scaling factor.");
            Sy = reader.nextDouble();
            System.out.println("Please enter the center of scale x value.");
            Cx = reader.nextInt();
            System.out.println("Please enter the center of scale y value.");
            Cy = reader.nextInt();
            matrix = Transformations.scale(matrix, Sx, Sy, Cx, Cy);
            drawMatrix(Color.BLUE, matrix);
            outputLines(matrix, numLines, outputLines);
        } else {
            System.out.println("Please enter the angle.");
            degrees = reader.nextDouble();
            System.out.println("Please enter the center of rotation x value.");
            Cx = reader.nextInt();
            System.out.println("Please enter the center of rotation y value.");
            Cy = reader.nextInt();
            matrix = Transformations.rotate(matrix, degrees, Cx, Cy);
            drawMatrix(Color.BLUE, matrix);
            outputLines(matrix, numLines, outputLines);
        }
        reader.close();
        

    } 

    public Matrix inputLines(String dataLines) {
        Matrix result;
        try {
            Scanner scanner = new Scanner(new File(dataLines));
            numLines = 0;
            if (scanner.hasNext()) {
                numLines = Integer.parseInt(scanner.nextLine());
            }
            scanner.useDelimiter(" ");
            double[][] data = new double[2][numLines*2];

            for (int i = 0; i < numLines*2; i++) {
                data[0][i] = scanner.nextInt();
                data[1][i] = scanner.nextInt();
            }
            result = new Matrix(data);
            
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        
        return result;
    }

    public void outputLines(Matrix matrix, int numLines, String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write("Num Lines: " + numLines + "\n" + matrix.toString() + "\n\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(canvas.getWidth(), canvas.getHeight());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //g2.translate(400.0, 400.0);
        g2.drawImage(canvas, null, null);
    }

    public void fillCanvas(Color c) {
        int color = c.getRGB();
        for (int x = 30; x < canvas.getWidth(); x++) {
            for (int y = 20; y < canvas.getHeight(); y++) {
                canvas.setRGB(x, y, color);
            }
        }
        repaint();
    }

    // Bresenham line drawing algorithm
    public void drawLine(Color c, int x1, int y1, int x2, int y2) {
        int color = c.getRGB();

        int d = 0;
 
        //Change in x and change in y.
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
 
        int dx2 = 2 * dx; 
        int dy2 = 2 * dy; 
 
        //Iteration variables
        int ix = 0;
        int iy = 0;

        //Direction control
        if (x1 < x2) {
            ix = 1;
        } else {
            ix = -1;
        }
        if (y1 < y2) {
            iy = 1;
        } else {
            iy = -1;
        }
 
        int x = x1;
        int y = y1;

        boolean filling = true;
        
        if (dx >= dy) { //Iterating in the x direction
            while (filling) {
                //Plot pixel
                canvas.setRGB(x, y, color);
                if (x == x2) {
                    break;
                }
                x += ix;
                d += dy2;
                if (d > dx) {
                    y += iy;
                    d -= dx2;
                }
            }

        } else { //Iterating in the y direction
            while (filling) {
                //Plot pixel
                canvas.setRGB(x, y, color);
                if (y == y2) {
                    break;
                }
                y += iy;
                d += dx2;
                if (d > dy) {

                    x += ix;
                    d -= dy2;

                }
            }
        }
        repaint();
    }

    public void drawMatrix(Color c, Matrix matrix) {
        int x1;
        int y1;
        int x2;
        int y2;

        for (int i = 0; i < matrix.N; i+=2) {
            x1 = (int)Math.floor(matrix.getValue(0, i));
            y1 = (int)Math.floor(matrix.getValue(1, i));
            x2 = (int)Math.floor(matrix.getValue(0, i+1));
            y2 = (int)Math.floor(matrix.getValue(1, i+1));
            if (x1 < 0 || x1 > 800) {
                System.out.println("Error: Transformation causes out of bounds error.");
                break;
            }
            if (x2 < 0 || x2 > 800) {
                System.out.println("Error: Transformation causes out of bounds error.");
                break;
            }
            if (y1 < 0 || y1 > 800) {
                System.out.println("Error: Transformation causes out of bounds error.");
                break;
            }
            if (y2 < 0 || y2 > 800) {
                System.out.println("Error: Transformation causes out of bounds error.");
                break;
            }
            
            drawLine(c, x1, y1, x2, y2);
        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int width = 800;
        int height = 800;

        JFrame frame = new JFrame("2D Transformations");

        Graph panel = new Graph(width, height);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
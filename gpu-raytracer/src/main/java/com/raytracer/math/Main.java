package com.raytracer.math;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        // Vector v1 = new Vector(1, 2, 0, 0);
        // Vector v2 = new Vector(2, 2, 0, 0);

        // Point p1 = new Point(2, 3, 5, 1);
        // Point p2 = new Point(1, 2, 3, 1);

        // Color c1 = ColorPalette.YELLOW.getColor();
        // Color c2 = ColorPalette.BLACK.getColor();

        // System.out.println(p1.add(v1).print());
        // System.out.println(p1.max(p2).print());

        // System.out.println("Länge des Vektors: " + v2.sqrMagnitude());
        // System.out.println("W-Komponente des normalisierten Vektors: " +
        // v2.normalized().getW());

        // System.out.println("Ähnlichkeit der Farben: " + c1.getSimilarity(c2));
        // System.out.println(c1.isSimilar(c2));

        // System.out.println(p1.subtract(p2).print());

        // Canvas c = new Canvas(100, 100, "image");
        // System.out.println(c.getImage().getRGB(1, 1));

        // for(int y = 0; y < 50; y++) {
        // for(int x = 0; x < 50; x++) {
        // c.setPixel(x, y, c1);
        // }
        // }

        // System.out.println(c.getImage().getRGB(1, 1));
        // System.out.println(c.getR(1, 1));
        // System.out.println(c.getG(1, 1));
        // System.out.println(c.getB(1, 1));

        // c.writeToFile();

        int dimension = 3;

        // double x = 0.0;

        // Double[][] matrixData = new Double[height][width];

        // // Initialize the matrix data with values
        // for (int i = 0; i < height; i++) {
        // for (int j = 0; j < width; j++) {
        // matrixData[j][i] = x;
        // x = x + 1.0;
        // }
        // }

        Double[][] matrixData = new Double[dimension][dimension];

        // Initialize the matrix data with values
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                matrixData[i][j] = 5.0;
            }
        }

        matrixData[1][2] = 6.0;
        // matrixData[1][3] = 7.0;
        matrixData[2][2] = 3.0;
        matrixData[1][0] = 2.0;
        // matrixData[0][3] = 9.0;

        // MatrixN m = new MatrixN(dimension, matrixData);
        // System.out.println(m);
        // System.out.println("-----------------");
        // System.out.println(m.identity());
        // System.out.println("-----------------");
        // System.out.println(m.determinant());
        // System.out.println("-----------------");
        // Matrix m = new Matrix(dimension, matrixData);
        // Matrix m2 = new Matrix(dimension);
        // Matrix m = new Matrix(matrixData);
        // Vector v = new Vector(2.0, 2.0, 3.0);
        // Point p = new Point(2.0, 3.0, 1.0);
        // System.out.println(m);
        // System.out.println("-----------------");
        // // System.out.println(m.scaling(2, 2, 2));
        // System.out.println("-----------------");
        // System.out.println(m.multiply(v));
        // System.out.println(m.multiply(p));

        // Sphere sphere = new Sphere();
        // Ray r = new Ray(new Point(0.0, 0.0, 0.0), new Vector(1.0, 0.0, 0.0));

        // IntersectionList intersections = sphere.intersect(r);
        // if (intersections.size() > 0) {
        //     Intersection firstIntersection = intersections.getIntersection(0);
        //     Intersection secondIntersection = intersections.getIntersection(1);

        //     System.out.println(firstIntersection); // Process the intersection as needed
        //     System.out.println(secondIntersection);
        // } else {
        //     System.out.println("No intersections found.");
        // }

        Vector v1 = new Vector(0, -1, 0);
        Vector v2 = new Vector(0.707011,0.707011,0);
        System.out.println(v1.reflect(v2));
    }
}

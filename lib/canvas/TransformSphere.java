package lib.canvas;

import java.io.IOException;

import lib.math.Matrix;
import lib.math.Point;
import lib.ray.Ray;
import lib.shapes.Sphere;

public class TransformSphere {
    
    public static void main(String[] args) throws IOException {
        int WIDTH = 400;
        int HEIGHT = 400;
        double WALL_Z = 10.0;
        double WALL_SIZE = 7.0;
        double HALF_SIZE = WALL_SIZE / 2.0;
        double PIXEL_SIZE = WALL_SIZE / WIDTH;

        Canvas c = new Canvas(WIDTH, HEIGHT);
        Point origin = new Point(0, 0, -5);
        Sphere sphere = new Sphere();

        Matrix rotateMatrix = Matrix.rotationZ(Math.PI/4);
        Matrix scaleMatrix = Matrix.scaling(0.5, 1, 1);
        Matrix transform = rotateMatrix.multiply(scaleMatrix);
        sphere.setTransformation(transform);

        for (int y = 0; y < HEIGHT; y++) {
            double worldY = HALF_SIZE - PIXEL_SIZE * y;

            for (int x = 0; x < WIDTH; x++) {
                double worldX = -HALF_SIZE + PIXEL_SIZE * x;
                Point position = new Point(worldX, worldY, WALL_Z);

                Ray ray = new Ray(origin, position);
                if(sphere.intersect(ray).size() == 0)
                        c.setPixel(x, y, new Color(0.2, 0.2, 0.8));
                    else
                        c.setPixel(x, y, new Color(1.0, 0.5, 0.0));
                }
            }

        c.writeToFile();
    }
}

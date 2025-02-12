package com.raytracer.canvas;

import java.io.IOException;

import com.raytracer.math.Matrix;
import com.raytracer.math.Point;
// import com.raytracer.math.Vector;
import com.raytracer.ray.Ray;
import com.raytracer.shapes.Sphere;

public class CreateRayCanvas {
    
    public static void main(String[] args) throws IOException {
        Canvas c = new Canvas(400, 400);
        Point camera = new Point(0, 0, -100);
        Sphere sphere = new Sphere();

        int xW = -c.getWidth()/2;
        int yW = -c.getHeight()/2;

        // double r = 0.0;
        // double g = 0.0;
        // double b = 0.0;

        for(int x = 0; x < c.getWidth(); x++) {
            yW = -c.getHeight()/2;

            for(int y = 0; y < c.getHeight(); y++) {

                // System.out.println("x: " + (xW + 0.5) + ", y: " + (yW + 0.5));
                // Ray ray = new Ray(camera, new Point(xW + 0.5, yW + 0.5, 0.0));
                // Ray ray = new Ray(camera, new Point(0.01*(xW + 0.5), 0.01*(yW + 0.5), 0.0));

                // c.setPixel(x, y, new Color(r, g, b));
                // g = g + 0.0001;
                // b = b + 0.0001;

                // Pixelkoordinaten: x = rot, y = grün, blau = 0
                // c.setPixel(x, y, new Color(x/1000.0, y/1000.0, 0));

                // Richtung des Strahls
                // c.setPixel(x, y, new Color(Math.abs(ray.getDirection().getX()), Math.abs(ray.getDirection().getY()), Math.abs(ray.getDirection().getZ())));

                // Länge des Strahls
                // Vector length = ray.getOrigin().subtract(new Point(xW, yW, 0)).divide(c.getWidth());
                // c.setPixel(x, y, new Color(length.sqrMagnitude(), length.sqrMagnitude(), length.sqrMagnitude()));



                //Richtung des Strahls
                // sphere.intersect(ray);
                // double r = Math.abs(ray.getDirection().getX())*10;
                // double g = Math.abs(ray.getDirection().getY())*10;
                // c.setPixel(x, y, new Color(r, g, 0.0));

                
                //getroffen/nicht getroffen
                // if(sphere.intersect(ray).size() == 0)
                //     c.setPixel(x, y, new Color(0.2, 0.2, 0.8));
                // else
                //     c.setPixel(x, y, new Color(1.0, 0.5, 0.0));


                //t-Wert
                // if(sphere.intersect(ray).size() != 0) {
                //     double value = sphere.intersect(ray).getIntersection(0).t() - 99.0;
                //     double r = value;
                //     double g = value;
                //     double b = value;
                //     c.setPixel(x, y, new Color(r, g, b));
                // } else {
                //     c.setPixel(x, y, new Color(0.2, 0.2, 0.8));
                // }
                

                // Normalenvektor an der Kugel
                // double value = 0.0;
                // if(sphere.intersect(ray).size() != 0) {
                //     value = sphere.intersect(ray).getIntersection(0).t();
                // } 
                // Point sp = ray.pointAt(value);
                // Vector normal = sphere.normalAt(sp).normalized();
                // double r = Math.abs(normal.getX());
                // double g = Math.abs(normal.getY());
                // double b = Math.abs(normal.getZ());
                // c.setPixel(x, y, new Color(r, g, b));


                
                Point position = new Point(0.01*xW, 0.01*yW, 10.0);
                Ray ray = new Ray(camera, position);

                // Matrix scaleMatrix = Matrix.scaling(1, 0.5, 1);

                // Matrix scaleMatrix = Matrix.scaling(0.5, 1, 1);

                Matrix rotateMatrix = Matrix.rotationZ(Math.PI/4);
                Matrix scaleMatrix = Matrix.scaling(0.5, 1, 1);
                Matrix transform = rotateMatrix.multiply(scaleMatrix);
                sphere.setTransformation(transform);

                // sphere.setTransformation(scaleMatrix);
                if(sphere.intersect(ray).size() == 0)
                    c.setPixel(x, y, new Color(0.2, 0.2, 0.8));
                else
                    c.setPixel(x, y, new Color(1.0, 0.5, 0.0));

                yW++; 
            }

            xW++;
        }

        c.writeToFile();
    }
}
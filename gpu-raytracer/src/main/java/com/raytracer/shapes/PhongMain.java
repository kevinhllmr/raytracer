package com.raytracer.shapes;

import java.io.IOException;

import com.raytracer.canvas.Canvas;
import com.raytracer.canvas.Color;
// import com.raytracer.math.Matrix;
import com.raytracer.math.Point;
// import com.raytracer.math.Vector;
import com.raytracer.scene.Camera;
import com.raytracer.scene.LightSource;
import com.raytracer.scene.PointLightSource;
import com.raytracer.scene.RayTracer;
import com.raytracer.scene.Scene;

public class PhongMain {
    
    public static void main(String[] args) throws IOException {
        Scene scene = new Scene();
        LightSource light = new PointLightSource(new Point(-10, 10, -10), new Color(1,1,1));
        Sphere sphere = new Sphere();
        
        Material mat = new Material();
        mat.setColor(new Color(1,0.2,1));

        // mat.setAmbient(0);
        // mat.setDiffuse(0);
        // mat.setSpecular(0);
        
        sphere.setMaterial(mat);

        scene.addLightSource(light);
        scene.addShapes(sphere);

        Camera camera = new Camera(800, 800, 90, new Point(0,0,-2), new Point(0,0,0));
    
        RayTracer rt = new RayTracer(scene, camera);
        Canvas canvas = rt.getRenderTarget();
        canvas.writeToFile();
    }
}

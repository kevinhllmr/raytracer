package com.raytracer.scene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.raytracer.canvas.Canvas;
import com.raytracer.canvas.Color;
import com.raytracer.math.Matrix;
import com.raytracer.math.Point;
import com.raytracer.math.Vector;
import com.raytracer.ray.IntersectionList;
import com.raytracer.ray.Ray;
import com.raytracer.shapes.Material;
import com.raytracer.shapes.Shape;
import com.raytracer.shapes.Sphere;

public class Scene {
    private List<Shape> shapes = new ArrayList<>();
    private List<LightSource> lightSources = new ArrayList<>();
    // private List<Material> materials;

    public void addShapes(Shape shape) {        
        shapes.add(shape);
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void addLightSource(LightSource light) {        
        lightSources.add(light);
    }

    public List<LightSource> getLightSources() {
        return lightSources;
    }  
    
    public static Scene DefaultScene() {
        Scene scene = new Scene();
        Sphere sphere1 = new Sphere();
        Sphere sphere2 = new Sphere();

        Matrix scaleMatrix = Matrix.scaling(0.5, 0.5, 0.5);
        sphere2.setTransformation(scaleMatrix);

        Material m = new Material();
        m.setColor(new Color(0.8, 1.0, 0.6));
        m.setDiffuse(0.7);
        m.setSpecular(0.2);
        sphere1.setMaterial(m);

        scene.addShapes(sphere1);
        scene.addShapes(sphere2);

        LightSource pointLight = new PointLightSource(new Point(-10, 10, -10), new Color(1, 1, 1));
        scene.addLightSource(pointLight);

        return scene;
    }

    public static void generateDefaultScene() throws IOException {
        LightSource light = new PointLightSource(new Point(-10, 10, -10), new Color(1,1,1));

        Sphere sphere1 = new Sphere();
        sphere1.setTransformation(Matrix.translation(-0.5, 1.0, 0.5));

        Sphere sphere2 = new Sphere();
        sphere2.setTransformation(Matrix.translation(1.5, 0.5, -0.5).multiply(Matrix.scaling(0.5)));

        Sphere sphere3 = new Sphere();
        sphere3.setTransformation(Matrix.translation(-1.5, 0.33, -0.75).multiply(Matrix.scaling(0.33)));

        Scene scene = new Scene();
        
        Material mat1 = new Material();
        mat1.setColor(new Color(1,0.2,1));
        mat1.setAmbient(0.4);
        mat1.setShininess(150);
        mat1.setDiffuse(0.9);
        sphere1.setMaterial(mat1);

        Material mat2 = new Material();
        mat2.setColor(new Color(0.3,0.9,0.15));
        mat2.setDiffuse(0.6);
        mat2.setSpecular(0.25);
        mat2.setShininess(0);
        sphere2.setMaterial(mat2);

        Material mat3 = new Material();
        mat3.setColor(new Color(0.9,0.4,0.05));
        mat3.setShininess(20);
        mat3.setDiffuse(0.7);
        mat3.setSpecular(0.4);
        sphere3.setMaterial(mat3);

        scene.addLightSource(light);
        scene.addShapes(sphere1);
        scene.addShapes(sphere2);
        scene.addShapes(sphere3);

        Camera camera = new Camera(1600, 800, 60, new Point(0,1.5,-5.0), new Point(0,1,0), new Vector(0,1,0));

        RayTracer rt = new RayTracer(scene, camera);
        Canvas canvas = rt.getRenderTarget();
        canvas.writeToFile();
    }

    public IntersectionList traceRay(Ray ray) {
        IntersectionList intersectionList = new IntersectionList();
        for (Shape shape : shapes) {
            intersectionList.addIntersectionList(shape.intersect(ray));         
        }

        return intersectionList;
    }
}

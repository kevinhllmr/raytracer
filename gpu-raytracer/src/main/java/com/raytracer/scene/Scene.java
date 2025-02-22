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

import com.raytracer.acceleration.*;

public class Scene {
    private List<Shape> shapes = new ArrayList<>();
    private List<LightSource> lightSources = new ArrayList<>();
    private KDTree kdTree; 
    private BVH bvh;
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

    public void buildKDTree() {
        this.kdTree = new KDTree(shapes); 
    }

    public void buildBVH() {
        this.bvh = new BVH(shapes);  
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

        long startBuild = System.nanoTime();

        System.gc();
        long beforeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        // scene.buildKDTree();
        scene.buildBVH();

        long afterMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long memoryUsed = afterMemory - beforeMemory;
        double memoryUsedKB = memoryUsed / 1024.0;
        String roundedMemoryUsedKB = String.format("%.2f", memoryUsedKB);

        if(scene.kdTree != null || scene.bvh != null) {
            String structureType = (scene.bvh != null) ? "BVH" : "KD-Tree";
            System.out.println(structureType + " memory usage: " + roundedMemoryUsedKB + " KB");
        }

        long endBuild = System.nanoTime();
        double buildTimeMs = (endBuild - startBuild) / 1_000_000.0;

        int buildMinutes = (int) (buildTimeMs / 60000);
        int buildSeconds = (int) ((buildTimeMs % 60000) / 1000);
        int buildMilliseconds = (int) (buildTimeMs % 1000);

        System.out.println("Build time: " + String.format("%dmin, %ds, %dms", buildMinutes, buildSeconds, buildMilliseconds));

        Camera camera = new Camera(1920, 1080, 60, new Point(0,1.5,-5.0), new Point(0,1,0), new Vector(0,1,0));

        RayTracer rt = new RayTracer(scene, camera);
        
        long startRender = System.nanoTime();
        Canvas canvas = rt.getRenderTarget();
        long endRender = System.nanoTime();

        double renderTimeMs = (endRender - startRender) / 1_000_000.0;

        int renderMinutes = (int) (renderTimeMs / 60000);
        int renderSeconds = (int) ((renderTimeMs % 60000) / 1000);
        int renderMilliseconds = (int) (renderTimeMs % 1000);

        System.out.println("Traversal Time: " + String.format("%dmin, %ds, %dms", renderMinutes, renderSeconds, renderMilliseconds));

        canvas.writeToFile();     
    }

    public static void generateGridScene() throws IOException {
        LightSource light = new PointLightSource(new Point(0, 50, 0), new Color(1, 1, 1));
    
        Scene scene = new Scene();
    
        int gridSize = 100; 
        double spacing = 3.5; 
    
        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                for (int z = 0; z < gridSize; z++) {
                    Sphere sphere = new Sphere();
                    sphere.setTransformation(Matrix.translation(
                        (x - gridSize / 2) * spacing, 
                        (y - gridSize / 2) * spacing, 
                        (z - gridSize / 2) * spacing
                    ));
    
                    Material mat = new Material();
                    mat.setColor(new Color(Math.random(), Math.random(), Math.random()));
                    mat.setDiffuse(0.7);
                    mat.setSpecular(0.3);
                    mat.setShininess(20);
                    sphere.setMaterial(mat);
    
                    scene.addShapes(sphere);
                }
            }
        }
    
        scene.addLightSource(light);

        long startBuild = System.nanoTime();

        System.gc();
        long beforeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        scene.buildKDTree();
        // scene.buildBVH();

        long afterMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long memoryUsed = afterMemory - beforeMemory;
        double memoryUsedKB = memoryUsed / 1024.0;
        String roundedMemoryUsedKB = String.format("%.2f", memoryUsedKB);

        if(scene.kdTree != null || scene.bvh != null) {
            String structureType = (scene.bvh != null) ? "BVH" : "KD-Tree";
            System.out.println(structureType + " memory usage: " + roundedMemoryUsedKB + " KB");
        }

        long endBuild = System.nanoTime();
        double buildTimeMs = (endBuild - startBuild) / 1_000_000.0;

        int buildMinutes = (int) (buildTimeMs / 60000);
        int buildSeconds = (int) ((buildTimeMs % 60000) / 1000);
        int buildMilliseconds = (int) (buildTimeMs % 1000);

        System.out.println("Build time: " + String.format("%dmin, %ds, %dms", buildMinutes, buildSeconds, buildMilliseconds));

        Camera camera = new Camera(400, 200, 100, new Point(0, 15, -30), new Point(0, 5, 0), new Vector(0, 1, 0));

        RayTracer rt = new RayTracer(scene, camera);

        long startRender = System.nanoTime();
        Canvas canvas = rt.getRenderTarget();
        long endRender = System.nanoTime();

        double renderTimeMs = (endRender - startRender) / 1_000_000.0;

        int renderMinutes = (int) (renderTimeMs / 60000);
        int renderSeconds = (int) ((renderTimeMs % 60000) / 1000);
        int renderMilliseconds = (int) (renderTimeMs % 1000);

        System.out.println("Traversal Time: " + String.format("%dmin, %ds, %dms", renderMinutes, renderSeconds, renderMilliseconds));

        canvas.writeToFile();
    }

    public static void generateRandomScene() throws IOException {
        LightSource light = new PointLightSource(new Point(0, 50, 0), new Color(1, 1, 1));
    
        Scene scene = new Scene();
    
        int numSpheres = 10_000_000;
        double maxX = 50, maxY = 50, maxZ = 50;
    
        for (int i = 0; i < numSpheres; i++) {
            double x = Math.random() * maxX - maxX / 2; 
            double y = Math.random() * maxY - maxY / 2;
            double z = Math.random() * maxZ - maxZ / 2;

            Sphere sphere = new Sphere();
            sphere.setTransformation(Matrix.translation(x, y, z));

            Material mat = new Material();
            mat.setColor(new Color(Math.random(), Math.random(), Math.random()));
            mat.setDiffuse(0.7);
            mat.setSpecular(0.3);
            mat.setShininess(20);
            sphere.setMaterial(mat);
    
            scene.addShapes(sphere);
        }
    

        scene.addLightSource(light);
    
        long startBuild = System.nanoTime();

        System.gc();
        long beforeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        // scene.buildKDTree();
        scene.buildBVH();
    
        long afterMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long memoryUsed = afterMemory - beforeMemory;
        double memoryUsedKB = memoryUsed / 1024.0;
        String roundedMemoryUsedKB = String.format("%.2f", memoryUsedKB);

        if (scene.kdTree != null || scene.bvh != null) {
            String structureType = (scene.bvh != null) ? "BVH" : "KD-Tree";
            System.out.println(structureType + " memory usage: " + roundedMemoryUsedKB + " KB");
        }
    
        long endBuild = System.nanoTime();
        double buildTimeMs = (endBuild - startBuild) / 1_000_000.0;
    
        int buildMinutes = (int) (buildTimeMs / 60000);
        int buildSeconds = (int) ((buildTimeMs % 60000) / 1000);
        int buildMilliseconds = (int) (buildTimeMs % 1000);
    
        System.out.println("Build time: " + String.format("%dmin, %ds, %dms", buildMinutes, buildSeconds, buildMilliseconds));
    
        Camera camera = new Camera(400, 200, 60, new Point(0, 15, -30), new Point(0, 5, 0), new Vector(0, 1, 0));
    
        RayTracer rt = new RayTracer(scene, camera);
    
        long startRender = System.nanoTime();
        Canvas canvas = rt.getRenderTarget();
        long endRender = System.nanoTime();
    
        double renderTimeMs = (endRender - startRender) / 1_000_000.0;
    
        int renderMinutes = (int) (renderTimeMs / 60000);
        int renderSeconds = (int) ((renderTimeMs % 60000) / 1000);
        int renderMilliseconds = (int) (renderTimeMs % 1000);
    
        System.out.println("Traversal Time: " + String.format("%dmin, %ds, %dms", renderMinutes, renderSeconds, renderMilliseconds));
    
        canvas.writeToFile();
    }    

    public IntersectionList traceRay(Ray ray) {
        IntersectionList intersectionList = new IntersectionList();

        if (bvh != null) {
            intersectionList = bvh.intersect(ray); 
        } else if (kdTree != null) {
            intersectionList = kdTree.intersect(ray); 
        } else {
            for (Shape shape : shapes) {
                intersectionList.addIntersectionList(shape.intersect(ray));
            }
        }
        return intersectionList;
    }
}

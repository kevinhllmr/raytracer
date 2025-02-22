package com.raytracer.scene;

import com.raytracer.canvas.Canvas;
import com.raytracer.canvas.Color;
import com.raytracer.canvas.ColorPalette;
import com.raytracer.math.Point;
import com.raytracer.ray.Intersection;
import com.raytracer.ray.IntersectionList;
import com.raytracer.ray.Ray;
import com.raytracer.shapes.Material;

public class RayTracer {
    
    private Scene scene;
    private Camera camera;
    private Canvas renderTarget;

    public RayTracer(Scene scene, Camera camera) {
        this.scene = scene;
        this.camera = camera;
    }

    public void render() {
        renderTarget = new Canvas(camera.getWidth(), camera.getHeight());

        int totalIntersectionCount = 0;

        for(int y = 0; y < camera.getHeight(); y++) {
            for(int x = 0; x < camera.getWidth(); x++) {
                Ray ray = camera.generateRay(x, y);

                IntersectionList intersectionList = scene.traceRay(ray); 
                totalIntersectionCount += intersectionList.getTotalIntersectionCount(); 

                Intersection hit = intersectionList.hit();

                if(hit != null) {
                    Material mat = hit.shape().getMaterial();
                    Point point = ray.pointAt(hit.t());

                    Color phong = mat.phongLighting(scene.getLightSources().get(0), point, ray.getDirection().negate(), hit.shape().normalAt(point));
                    renderTarget.setPixel(x, y, phong);

                } else {
                    renderTarget.setPixel(x, y, ColorPalette.CYAN.getColor());
                }
            }
        }

        System.out.println("Total Intersections: " + totalIntersectionCount);
    }

    public Canvas getRenderTarget() {
        render();
        return renderTarget;
    }
}

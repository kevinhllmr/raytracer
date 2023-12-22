package lib.scene;

import lib.canvas.Canvas;
import lib.canvas.Color;
import lib.canvas.ColorPalette;
import lib.math.Point;
import lib.ray.Intersection;
import lib.ray.Ray;
import lib.shapes.Material;

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

        for(int y = 0; y < camera.getHeight(); y++) {
            for(int x = 0; x < camera.getWidth(); x++) {
                Ray ray = camera.generateRay(x, y);
                Intersection hit = scene.traceRay(ray).hit();

                if(hit != null) {
                    // renderTarget.setPixel(x, y, ColorPalette.ORANGE.getColor());
                    Material mat = hit.shape().getMaterial();
                    Point point = ray.pointAt(hit.t());

                    Color phong = mat.phongLighting(scene.getLightSources().get(0), point, ray.getDirection().negate(), hit.shape().normalAt(point));
                    renderTarget.setPixel(x, y, phong);

                } else {
                    renderTarget.setPixel(x, y, ColorPalette.CYAN.getColor());
                }
            }
        }
    }

    public Canvas getRenderTarget() {
        render();
        return renderTarget;
    }
}

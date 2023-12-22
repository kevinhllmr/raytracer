package lib.scene;

import java.io.IOException;

// import lib.canvas.Canvas;
// import lib.canvas.ColorPalette;
// import lib.math.Matrix;
// import lib.math.Point;
// import lib.math.Vector;
// import lib.ray.IntersectionList;
// import lib.ray.Ray;
// import lib.shapes.Sphere;

public class Main {
    
    // public static void main(String[] args) throws IOException {
    //     Sphere sphere = new Sphere();
    //     sphere.setTransformation(Matrix.scaling(0.5));

    //     Scene scene = new Scene();
    //     scene.addShapes(sphere);

    //     // Camera camera = new Camera(800, 600, 7.7, new Point(0,0,-10), new Point(0,0,0), new Vector(0,1,0));
    //     // Camera camera = new Camera(600, 600, 11.4, new Point(0,0,-10), new Point(1,1,0), new Vector(0,1,0));
    //     Camera camera = new Camera(600, 600, 2.7, new Point(10,10,-10), new Point(0,0,0), new Vector(0,1,0));

    //     RayTracer rt = new RayTracer(scene, camera);
    //     Canvas canvas = rt.getRenderTarget();
    //     canvas.writeToFile();
    // }


    public static void main(String[] args) throws IOException {
        Scene.generateDefaultScene();
    }


    // public static void main(String[] args) throws IOException {
    //     Scene scene = new Scene();
    //     Camera camera = new Camera(400, 400, 90, new Point(0,0,-5), new Point(0,0,0), new Vector(0,1,0));
    
    //     for(int y = -3; y <= 3; y++) {
    //         for(int x = -3; x <= 3; x++) {
    //             Sphere sphere = new Sphere();
    //             sphere.setTransformation(Matrix.translation(x, y, 0).multiply(Matrix.scaling(0.4)));
    //             scene.addShapes(sphere);
    //         }
    //     }
    
    //     RayTracer rt = new RayTracer(scene, camera);
    //     Canvas canvas = rt.getRenderTarget();
    //     canvas.writeToFile();
    // }
}

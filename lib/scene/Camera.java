package lib.scene;

import lib.math.Matrix;
import lib.math.Point;
import lib.math.Vector;
import lib.ray.Ray;

public class Camera {
    
    private int width;
    private int height;
    private double fov;
    private Matrix transform;

    private double halfHeight;
    private double halfWidth;
    private double pixelSize;

    public Camera(int width, int height, double fov) {
        this.width = width;
        this.height = height;
        this.fov = fov;
        this.transform = Matrix.getIdentity();

        calcInternParameters();
    }

    public Camera(int width, int height, double fov, Matrix viewTransform) {
        this.width = width;
        this.height = height;
        this.fov = fov;
        this.transform = viewTransform;

        calcInternParameters();
    }

    public Camera(int width, int height, double fov, Point position, Point lookAt, Vector up) {
        this.width = width;
        this.height = height;
        this.fov = fov;
        this.transform = Matrix.viewTransform(position, lookAt, up);

        calcInternParameters();
    }

    public Camera(int width, int height, double fov, Point position, Point lookAt) {
        this.width = width;
        this.height = height;
        this.fov = fov;
        this.transform = Matrix.viewTransform(position, lookAt, new Vector(0,1,0));

        calcInternParameters();
    }

    public void calcInternParameters() {
        double aspectRatio = (double) width / (double) height;
        double halfView = Math.tan(Math.toRadians(fov)/2);

        if(aspectRatio < 1) {
            halfHeight = halfView;
            halfWidth = halfView * aspectRatio;

        } else {
            halfWidth = halfView;
            halfHeight = halfView / aspectRatio;
        }

        pixelSize = (2 * halfWidth) / (double) width;
    }

    public Ray generateRay(int pixelX, int pixelY) {
        double xOffset = (pixelX + 0.5) * pixelSize;
        double yOffset = (pixelY + 0.5) * pixelSize;

        double worldX = halfWidth - xOffset;
        double worldY = halfHeight - yOffset;

        Point pixel = transform.invert().multiply(new Point(worldX, worldY, -1));
        Point origin = transform.invert().multiply(new Point(0, 0, 0));
        Vector direction = pixel.subtract(origin).normalized();

        return new Ray(origin, direction);
    }

    public void setTransformation(Matrix transform) {
        this.transform = transform;
    }

    public double getFov() {
        return fov;
    }

    public double getHalfHeight() {
        return halfHeight;
    }

    public double getHalfWidth() {
        return halfWidth;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getPixelSize() {
        return pixelSize;
    }

    public Matrix getTransformation() {
        return transform;
    }
}

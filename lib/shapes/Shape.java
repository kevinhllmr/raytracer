package lib.shapes;

import lib.math.Matrix;
import lib.math.Point;
import lib.math.Vector;
import lib.ray.IntersectionList;
import lib.ray.Ray;

public abstract class Shape {

    private Matrix transformation;
    private Material material = new Material();

    public Shape() {
        this.transformation = Matrix.getIdentity();
    }

    public void setTransformation(Matrix transformation) {
        this.transformation = transformation;
    }
    
    public Matrix getTransformation() {
        return transformation;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public IntersectionList intersect(Ray worldRay) {
        Ray ray = worldRay.transform(this.transformation.invert());
        return localIntersect(ray);
    }

    protected abstract IntersectionList localIntersect(Ray localRay);

    public Vector normalAt(Point worldPoint) {
        return localNormalAt(worldPoint);
    }

    public abstract Vector localNormalAt(Point point);
}

package lib.shapes;

import lib.math.Point;
import lib.math.Vector;
import lib.ray.Intersection;
import lib.ray.IntersectionList;
import lib.ray.Ray;

public class Sphere extends Shape {

    @Override
    public IntersectionList localIntersect(Ray ray) {
        IntersectionList intersectionList = new IntersectionList();

        double sphereRadius = 1.0;
        Point sphereCenter = new Point(0, 0, 0);

        Vector sphereToRay = ray.getOrigin().subtract(sphereCenter);

        double a = ray.getDirection().dot(ray.getDirection());
        double b = 2 * ray.getDirection().dot(sphereToRay);
        double c = sphereToRay.dot(sphereToRay) - sphereRadius * sphereRadius;
        double discriminant = b * b - 4 * a * c;

        if (discriminant >= 0) {
            double sqrtDiscriminant = Math.sqrt(discriminant);
            double t1 = (-b - sqrtDiscriminant) / (2 * a);
            double t2 = (-b + sqrtDiscriminant) / (2 * a);

            intersectionList.addIntersection(new Intersection(t1, this));
            intersectionList.addIntersection(new Intersection(t2, this));
        }

        return intersectionList;
    }

    @Override
    public Vector localNormalAt(Point point) {
        // return localPoint.subtract(new Point(0, 0, 0)).normalized();
        Point localPoint = getTransformation().invert().multiply(point);
        Vector localNormal = localPoint.subtract(new Point(0,0,0));
        Vector worldNormal = getTransformation().invert().transpose().multiply(localNormal);
        
        return new Vector(worldNormal.x(), worldNormal.y(), worldNormal.z(), 0).normalized();
    }
}

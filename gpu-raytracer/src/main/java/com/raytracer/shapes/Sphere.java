package com.raytracer.shapes;

import com.raytracer.math.AABB;
import com.raytracer.math.Matrix;
import com.raytracer.math.Point;
import com.raytracer.math.Vector;
import com.raytracer.ray.Intersection;
import com.raytracer.ray.IntersectionList;
import com.raytracer.ray.Ray;

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
    public AABB getBoundingBox() {
        Point sphereCenter = this.getTransformation().multiply(new Point(0, 0, 0));
        double sphereRadius = 1.0;

        Matrix transformation = this.getTransformation();
        double scaleX = transformation.get(0, 0); 
        double scaleY = transformation.get(1, 1); 
        double scaleZ = transformation.get(2, 2);  

        // Adjust the bounding box to account for scaling
        double minX = sphereCenter.x() - sphereRadius * scaleX;
        double minY = sphereCenter.y() - sphereRadius * scaleY;
        double minZ = sphereCenter.z() - sphereRadius * scaleZ;
        double maxX = sphereCenter.x() + sphereRadius * scaleX;
        double maxY = sphereCenter.y() + sphereRadius * scaleY;
        double maxZ = sphereCenter.z() + sphereRadius * scaleZ;

        return new AABB(minX, minY, minZ, maxX, maxY, maxZ);
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

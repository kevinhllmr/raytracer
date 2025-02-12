package com.raytracer.ray;

import com.raytracer.math.Matrix;
import com.raytracer.math.Point;
import com.raytracer.math.Vector;

public class Ray {
    
    private final Point origin;
    private final Vector direction;

    public Ray(Point origin, Vector direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public Ray(Point origin, Point target) {
        this.origin = origin;
        Vector direction = new Vector(target.x() - origin.x(), target.y() - origin.y(), target.z() - origin.z());
        this.direction = direction.normalized();
    }

    public Point getOrigin() {
        return origin;
    }

    public Vector getDirection() {
        return direction;
    }

    public Point pointAt(double t) {
        double x = direction.x() * t;
        double y = direction.y() * t;
        double z = direction.z() * t;
        Vector v = new Vector(x, y, z);

        return new Point(origin.add(v));
    }

    public Ray transform(Matrix m) {
        Point tempP = m.multiply(origin);
        Vector tempD = m.multiply(direction);

        return new Ray(tempP, tempD);
    }
}

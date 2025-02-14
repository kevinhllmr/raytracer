package com.raytracer.math;

import com.raytracer.ray.Ray;
import com.raytracer.shapes.Shape;

public class AABB {
    public double minX, minY, minZ;
    public double maxX, maxY, maxZ;

    public AABB(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
    }

    public boolean intersects(Ray ray) {
        double tMin = (minX - ray.getOrigin().x()) / ray.getDirection().x();
        double tMax = (maxX - ray.getOrigin().x()) / ray.getDirection().x();

        if (tMin > tMax) {
            double temp = tMin;
            tMin = tMax;
            tMax = temp;
        }

        double tyMin = (minY - ray.getOrigin().y()) / ray.getDirection().y();
        double tyMax = (maxY - ray.getOrigin().y()) / ray.getDirection().y();

        if (tyMin > tyMax) {
            double temp = tyMin;
            tyMin = tyMax;
            tyMax = temp;
        }

        if ((tMin > tyMax) || (tyMin > tMax)) return false;

        if (tyMin > tMin) tMin = tyMin;
        if (tyMax < tMax) tMax = tyMax;

        double tzMin = (minZ - ray.getOrigin().z()) / ray.getDirection().z();
        double tzMax = (maxZ - ray.getOrigin().z()) / ray.getDirection().z();

        if (tzMin > tzMax) {
            double temp = tzMin;
            tzMin = tzMax;
            tzMax = temp;
        }

        return !((tMin > tzMax) || (tzMin > tMax));
    }

    public double getCenter(int axis) {
        switch (axis) {
            case 0:
                return (minX + maxX) / 2;
            case 1:
                return (minY + maxY) / 2;
            case 2:
                return (minZ + maxZ) / 2;
            default:
                throw new IllegalArgumentException("Invalid axis index: " + axis);
        }
    }

    public double getSurfaceArea() {
        double width = maxX - minX;
        double height = maxY - minY;
        double depth = maxZ - minZ;
        return 2.0 * (width * height + width * depth + height * depth);
    }

    public static AABB enclose(Iterable<Shape> objects) {
        double minX = Double.POSITIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double minZ = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;
        double maxZ = Double.NEGATIVE_INFINITY;

        for (Shape obj : objects) {
            AABB box = obj.getBoundingBox();
            minX = Math.min(minX, box.minX);
            minY = Math.min(minY, box.minY);
            minZ = Math.min(minZ, box.minZ);
            maxX = Math.max(maxX, box.maxX);
            maxY = Math.max(maxY, box.maxY);
            maxZ = Math.max(maxZ, box.maxZ);
        }

        return new AABB(minX, minY, minZ, maxX, maxY, maxZ);
    }
}

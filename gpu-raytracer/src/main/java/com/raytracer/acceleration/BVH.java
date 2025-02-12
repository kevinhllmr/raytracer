package com.raytracer.acceleration;

import com.raytracer.ray.Ray;
import com.raytracer.math.AABB;
import com.raytracer.ray.IntersectionList;
import com.raytracer.shapes.Shape;
import java.util.List;

public class BVH {
    private BVHNode root;

    public BVH(List<Shape> shapes) {
        root = buildBVH(shapes);
    }

    private BVHNode buildBVH(List<Shape> shapes) {
        if (shapes.isEmpty()) {
            return null;
        }

        AABB aabb = AABB.enclose(shapes);

        if (shapes.size() == 1) {
            return new BVHNode(aabb, shapes.get(0), null, null); 
        }

        int axis = longestAxis(aabb);

        shapes.sort((shape1, shape2) -> {
            double center1 = shape1.getBoundingBox().getCenter(axis); 
            double center2 = shape2.getBoundingBox().getCenter(axis);
            return Double.compare(center1, center2);
        });

        int mid = shapes.size() / 2;
        List<Shape> leftShapes = shapes.subList(0, mid);
        List<Shape> rightShapes = shapes.subList(mid, shapes.size());

        BVHNode leftNode = buildBVH(leftShapes);
        BVHNode rightNode = buildBVH(rightShapes);


        return new BVHNode(aabb, null, leftNode, rightNode); 
    }

    private int longestAxis(AABB aabb) {
        double xExtent = aabb.maxX - aabb.minX;
        double yExtent = aabb.maxY - aabb.minY;
        double zExtent = aabb.maxZ - aabb.minZ;

        if (xExtent > yExtent && xExtent > zExtent) {
            return 0; 
        } else if (yExtent > zExtent) {
            return 1; 
        } else {
            return 2;
        }
    }

    public IntersectionList intersect(Ray ray) {
        IntersectionList intersections = new IntersectionList();
        if (root != null) {
            root.intersect(ray, intersections);
        }
        return intersections;
    }
}

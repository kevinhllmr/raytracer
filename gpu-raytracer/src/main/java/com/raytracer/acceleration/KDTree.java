package com.raytracer.acceleration;

import com.raytracer.shapes.Shape;
import com.raytracer.ray.Ray;
import com.raytracer.math.AABB;
import com.raytracer.ray.IntersectionList;

import java.util.List;
import java.util.ArrayList;

public class KDTree {

    private static final int K = 3;
    private KDNode root;

    public KDTree(List<Shape> shapes) {
        this.root = buildTree(shapes, 0);
    }

    private KDNode buildTree(List<Shape> shapes, int depth) {
        if (shapes.isEmpty()) {
            return null;
        }

        int axis = depth % K;
        shapes.sort((a, b) -> {
            AABB boxA = a.getBoundingBox();
            AABB boxB = b.getBoundingBox();
            double midA = (axis == 0) ? (boxA.minX + boxA.maxX) / 2 : (axis == 1) ? (boxA.minY + boxA.maxY) / 2 : (boxA.minZ + boxA.maxZ) / 2;
            double midB = (axis == 0) ? (boxB.minX + boxB.maxX) / 2 : (axis == 1) ? (boxB.minY + boxB.maxY) / 2 : (boxB.minZ + boxB.maxZ) / 2;
            return Double.compare(midA, midB);
        });

        int medianIndex = shapes.size() / 2;
        Shape median = shapes.get(medianIndex);

        List<Shape> leftShapes = new ArrayList<>(shapes.subList(0, medianIndex));
        List<Shape> rightShapes = new ArrayList<>(shapes.subList(medianIndex + 1, shapes.size()));

        KDNode node = new KDNode(median, buildTree(leftShapes, depth + 1), buildTree(rightShapes, depth + 1));

        return node;
    }

    public IntersectionList intersect(Ray ray) {
        return intersectNode(ray, root);
    }

    private IntersectionList intersectNode(Ray ray, KDNode node) {
        if (node == null) {
            return new IntersectionList();
        }

        IntersectionList intersections = new IntersectionList();

        AABB box = node.shape.getBoundingBox();
        if (!box.intersects(ray)) {
            return intersections;
        }

        intersections.addIntersectionList(node.shape.intersect(ray));


        if (ray.getOrigin().x() < box.maxX) {
            intersections.addIntersectionList(intersectNode(ray, node.left));
        }
        if (ray.getOrigin().x() > box.minX) {
            intersections.addIntersectionList(intersectNode(ray, node.right));
        }

        return intersections;
    }

    private static class KDNode {
        Shape shape;
        KDNode left;
        KDNode right;

        public KDNode(Shape shape, KDNode left, KDNode right) {
            this.shape = shape;
            this.left = left;
            this.right = right;
        }
    }
}

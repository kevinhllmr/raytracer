package com.raytracer.acceleration;

import com.raytracer.shapes.Shape;
import com.raytracer.ray.Ray;
import com.raytracer.math.AABB;
import com.raytracer.ray.IntersectionList;

import java.util.List;
import java.util.Comparator;

public class KDTree {
    private KDNode root;

    public KDTree(List<Shape> shapes) {
        this.root = buildTree(shapes, 0);
    }

    private KDNode buildTree(List<Shape> shapes, int depth) {
        if (shapes.isEmpty()) return null;
        if (shapes.size() == 1) return new KDNode(AABB.enclose(shapes), shapes);

        int axis = depth % 3;
        shapes.sort(Comparator.comparingDouble(s -> s.getBoundingBox().getCenter(axis)));
        int mid = shapes.size() / 2;

        return new KDNode(AABB.enclose(shapes), null,
                buildTree(shapes.subList(0, mid), depth + 1),
                buildTree(shapes.subList(mid, shapes.size()), depth + 1), axis);
    }

    public IntersectionList intersect(Ray ray) {
        return intersectNode(ray, root);
    }

    private IntersectionList intersectNode(Ray ray, KDNode node) {
        if (node == null || !node.bbox.intersects(ray)) return new IntersectionList();

        IntersectionList intersections = new IntersectionList();
        if (node.shapes != null) node.shapes.forEach(s -> intersections.addIntersectionList(s.intersect(ray)));
        else {
            intersections.addIntersectionList(intersectNode(ray, ray.getOrigin().get(node.axis) < node.bbox.getCenter(node.axis) ? node.left : node.right));
            intersections.addIntersectionList(intersectNode(ray, ray.getOrigin().get(node.axis) >= node.bbox.getCenter(node.axis) ? node.left : node.right));
        }
        return intersections;
    }

    private static class KDNode {
        AABB bbox;
        List<Shape> shapes;
        KDNode left, right;
        int axis;

        KDNode(AABB bbox, List<Shape> shapes) {
            this.bbox = bbox;
            this.shapes = shapes;
        }

        KDNode(AABB bbox, List<Shape> shapes, KDNode left, KDNode right, int axis) {
            this(bbox, shapes);
            this.left = left;
            this.right = right;
            this.axis = axis;
        }
    }
}
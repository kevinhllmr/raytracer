package com.raytracer.acceleration;

import com.raytracer.shapes.Shape;
import com.raytracer.ray.Ray;
import com.raytracer.math.AABB;
import com.raytracer.ray.IntersectionList;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class KDTree {
    private static final int MAX_DEPTH = 20; 
    private static final int MIN_SHAPES_PER_LEAF = 1;

    private KDNode root;

    public KDTree(List<Shape> shapes) {
        this.root = buildTree(shapes, 0);
    }

    private KDNode buildTree(List<Shape> shapes, int depth) {
        if (shapes.isEmpty()) return null;

        if (shapes.size() <= MIN_SHAPES_PER_LEAF || depth >= MAX_DEPTH) {
            return new KDNode(null, null, AABB.enclose(shapes), shapes);
        }

        int bestAxis = -1;
        double bestSplit = 0;
        double bestCost = Double.MAX_VALUE;
        
        for (int axis = 0; axis < 3; axis++) {
            final int currentAxis = axis;
            shapes.sort(Comparator.comparingDouble(s -> s.getBoundingBox().getCenter(currentAxis)));
            
            for (int i = 1; i < shapes.size(); i++) {
                double splitPos = shapes.get(i).getBoundingBox().getCenter(currentAxis);
        
                List<Shape> left = new ArrayList<>();
                List<Shape> right = new ArrayList<>();
        
                for (Shape shape : shapes) {
                    if (shape.getBoundingBox().getCenter(currentAxis) < splitPos) left.add(shape);
                    else right.add(shape);
                }
        
                double cost = sahCost(left.size(), right.size(), AABB.enclose(left), AABB.enclose(right));
                if (cost < bestCost) {
                    bestCost = cost;
                    bestAxis = currentAxis; 
                    bestSplit = splitPos;
                }
            }
        }
        
        if (bestAxis == -1) {
            return new KDNode(null, null, AABB.enclose(shapes), shapes);
        }

        List<Shape> leftShapes = new ArrayList<>();
        List<Shape> rightShapes = new ArrayList<>();
        for (Shape shape : shapes) {
            if (shape.getBoundingBox().getCenter(bestAxis) < bestSplit) {
                leftShapes.add(shape);
            } else {
                rightShapes.add(shape);
            }
        }

        return new KDNode(buildTree(leftShapes, depth + 1), buildTree(rightShapes, depth + 1), AABB.enclose(shapes), null);
    }

    private double sahCost(int leftSize, int rightSize, AABB leftBox, AABB rightBox) {
        double leftArea = leftBox.getSurfaceArea();
        double rightArea = rightBox.getSurfaceArea();
        return leftSize * leftArea + rightSize * rightArea;
    }

    public IntersectionList intersect(Ray ray) {
        return intersectNode(ray, root);
    }

    private IntersectionList intersectNode(Ray ray, KDNode node) {
        if (node == null || !node.bbox.intersects(ray)) return new IntersectionList();

        IntersectionList intersections = new IntersectionList();

        if (node.shapes != null) {
            for (Shape shape : node.shapes) {
                intersections.addIntersectionList(shape.intersect(ray));
            }
            return intersections;
        }

        boolean leftFirst = (node.left != null && ray.getOrigin().get(node.splitAxis) < node.splitPosition);

        KDNode first = leftFirst ? node.left : node.right;
        KDNode second = leftFirst ? node.right : node.left;

        if (first != null && first.bbox.intersects(ray)) {
            intersections.addIntersectionList(intersectNode(ray, first));
        }
        if (second != null && second.bbox.intersects(ray)) {
            intersections.addIntersectionList(intersectNode(ray, second));
        }

        return intersections;
    }

    private static class KDNode {
        KDNode left, right;
        AABB bbox;
        List<Shape> shapes;
        int splitAxis;
        double splitPosition;

        public KDNode(KDNode left, KDNode right, AABB bbox, List<Shape> shapes) {
            this.left = left;
            this.right = right;
            this.bbox = bbox;
            this.shapes = shapes;
        }
    }
}

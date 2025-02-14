package com.raytracer.acceleration;

import com.raytracer.ray.Ray;
import com.raytracer.math.AABB;
import com.raytracer.ray.IntersectionList;
import com.raytracer.shapes.Shape;

public class BVHNode {
    private AABB boundingBox; 
    private Shape shape;    
    private BVHNode left;  
    private BVHNode right;   

    public BVHNode(AABB boundingBox, Shape shape, BVHNode left, BVHNode right) {
        this.boundingBox = boundingBox;
        this.shape = shape;
        this.left = left;
        this.right = right;
    }

    public BVHNode(AABB boundingBox, BVHNode left, BVHNode right) {
        this.boundingBox = boundingBox;
        this.left = left;
        this.right = right;
    }

    public void intersect(Ray ray, IntersectionList intersections) {
        if (boundingBox.intersects(ray)) {
            if (shape != null) {
                intersections.addIntersectionList(shape.intersect(ray));
            } else {
                if (left != null) left.intersect(ray, intersections);
                if (right != null) right.intersect(ray, intersections);
            }
        }
    }    
}

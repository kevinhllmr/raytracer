package com.raytracer.ray;

import com.raytracer.shapes.Shape;

public record Intersection(double t, Shape shape) {
    
}
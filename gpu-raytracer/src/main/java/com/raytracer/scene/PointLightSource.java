package com.raytracer.scene;

import com.raytracer.canvas.Color;
import com.raytracer.math.Point;

public class PointLightSource extends LightSource {

    public PointLightSource(Point position, Color color) {
        super(position, color);
    }

    public PointLightSource(Point position, Color color, int intensity) {
        super(position, color, intensity);
    }

}

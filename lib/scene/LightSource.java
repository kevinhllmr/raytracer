package lib.scene;

import lib.canvas.Color;
import lib.math.Calc;
import lib.math.Point;

public class LightSource {
    
    private Point position;
    private Color color;
    private double intensity = 1;

    public LightSource(Point position, Color color) {
        this.position = position;
        this.color = color;
    }

    public LightSource(Point position, Color color, double intensity) {
        this.position = position;
        this.color = color;
        this.intensity = intensity;
    }

    public Color getColor() {
        return color;
    }

    public double getIntensity() {
        return intensity;
    }

    public Point getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LightSource l2 = (LightSource) obj;
        return position.equals(l2.position) && color.equals(l2.color) && Calc.approxEqual(intensity, l2.intensity);
    }
}

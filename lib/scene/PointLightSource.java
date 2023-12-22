package lib.scene;

import lib.canvas.Color;
import lib.math.Point;

public class PointLightSource extends LightSource {

    public PointLightSource(Point position, Color color) {
        super(position, color);
    }

    public PointLightSource(Point position, Color color, int intensity) {
        super(position, color, intensity);
    }

}

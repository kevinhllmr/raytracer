package test;

import org.junit.jupiter.api.Test;

import lib.canvas.Color;
import lib.math.Calc;
import lib.math.Point;
import lib.scene.LightSource;
import lib.scene.PointLightSource;

import static org.junit.Assert.*;

public class LightSourceTest {
    
    @Test
    public void testPositionAndIntensity() {
        Color color = new Color(1, 1, 1);
        Point position = new Point(0, 0, 0);
        LightSource light = new PointLightSource(position, color);

        assertEquals(position, light.getPosition());
        assertEquals(color, light.getColor());
        assertEquals(1.0, light.getIntensity(), Calc.EPSILON);
    }
}

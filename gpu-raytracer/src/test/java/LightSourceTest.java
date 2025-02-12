

import org.junit.jupiter.api.Test;

import com.raytracer.canvas.Color;
import com.raytracer.math.Calc;
import com.raytracer.math.Point;
import com.raytracer.scene.LightSource;
import com.raytracer.scene.PointLightSource;

import static org.junit.jupiter.api.Assertions.*;

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

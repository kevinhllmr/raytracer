

import org.junit.jupiter.api.Test;

import com.raytracer.canvas.Canvas;
import com.raytracer.canvas.Color;

import static org.junit.jupiter.api.Assertions.*;

public class CanvasTest {
    
    @Test
    public void testCreateCanvasWithoutName() {
        Canvas c = new Canvas(300, 300);

        assertEquals(300, c.getWidth());
        assertEquals(300, c.getHeight());
        assertEquals("", c.getName());
    }

    @Test
    public void testCreateNamedCanvas() {
        Canvas c = new Canvas(300, 300, "image");

        assertEquals(300, c.getWidth());
        assertEquals(300, c.getHeight());
        assertEquals("image", c.getName());
    }

    @Test
    public void testSettingPixelInCanvas() {
        Canvas c = new Canvas(300, 300);
        Color co = new Color(0.5, 0.5, 0.5);
        c.setPixel(30, 30, co);

        assertEquals(c.getPixel(30, 30), co.getRGB());
    }
}

import org.junit.jupiter.api.Test;

import com.raytracer.math.Calc;
import com.raytracer.math.Matrix;
import com.raytracer.math.Point;
import com.raytracer.math.Vector;
import com.raytracer.ray.Ray;
import com.raytracer.scene.Camera;

import static org.junit.jupiter.api.Assertions.*;

public class CameraTest {
    
    @Test
    public void testConstructCamera() {
        Camera c = new Camera(160, 120, 90);

        assertEquals(160, c.getWidth());
        assertEquals(120, c.getHeight());
        assertEquals(90, c.getFov(), Calc.EPSILON);
        assertEquals(Matrix.getIdentity().toString(), c.getTransformation().toString());
    }

    @Test
    public void testPixelSizeHorizontalCanvas() {
        Camera c = new Camera(200, 125, 90);
        
        assertEquals(0.01, c.getPixelSize(), Calc.EPSILON);
    }

    @Test
    public void testPixelSizeVerticalCanvas() {
        Camera c = new Camera(125, 200, 90);
        
        assertEquals(0.01, c.getPixelSize(), Calc.EPSILON);
    }

    @Test
    public void testConstructRayThroughCenterOfCanvas() {
        Camera c = new Camera(201, 101, 90);
        Ray r = c.generateRay(100, 50);
        
        assertTrue(new Point(0, 0, 0).equals(r.getOrigin()));
        assertTrue(new Vector(0, 0, -1).equals(r.getDirection()));
    }

    @Test
    public void testConstructRayThroughCornerOfCanvas() {
        Camera c = new Camera(201, 101, 90);
        Ray r = c.generateRay(0, 0);
        
        assertTrue(new Point(0, 0, 0).equals(r.getOrigin()));
        assertTrue(new Vector(0.665186, 0.332593, -0.668512).equals(r.getDirection()));
    }

    @Test
    public void testConstructRayCameraTransformed() {
        Camera c = new Camera(201, 101, 90);
        c.setTransformation(Matrix.rotationY(0.785398).multiply(Matrix.translation(0, -2, 5)));
        Ray r = c.generateRay(100, 50);
        
        assertTrue(new Point(0, 2, -5).equals(r.getOrigin()));
        assertTrue(new Vector(0.707106, 0, -0.707106).equals(r.getDirection()));
    }
}

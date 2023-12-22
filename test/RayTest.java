package test;

import org.junit.jupiter.api.Test;

import lib.math.Point;
import lib.math.Vector;
import lib.ray.Ray;

import static org.junit.Assert.*;

public class RayTest {
    
    @Test
    public void testCreateAndQueryRay() {
        Point origin = new Point(1, 2, 3);
        Vector direction = new Vector(4, 5, 6);
        Ray ray = new Ray(origin, direction);

        assertTrue(origin.equals(ray.getOrigin()));
        assertTrue(direction.equals(ray.getDirection()));
    }

    @Test
    public void testCreateAndQueryRayFromTwoPoints() {
        Point origin = new Point(1, 2, 3);
        Point target = new Point(3, 4, 5);
        Ray ray = new Ray(origin, target);

        assertTrue(origin.equals(ray.getOrigin()));
        assertTrue((target.subtract(origin)).normalized().equals(ray.getDirection()));
    }

    @Test
    public void testComputePointFromDistance() {
        Ray ray = new Ray(new Point(2, 3, 4), new Vector(1, 0, 0));

        assertTrue(new Point(4.5, 3, 4).equals(ray.pointAt(2.5)));
    }
}

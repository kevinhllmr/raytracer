package test;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import lib.math.Point;
import lib.math.Vector;

public class PointTest {
    
    @Test
    public void shouldPrintPointData() {
        Point p = new Point(1,2,3);
        
        assertEquals(p.toString(), "(1.0, 2.0, 3.0, 1.0)");
    }

    @Test
    public void shouldSubtractTwoPoints() {
        Point p1 = new Point(3,1,7);
        Point p2 = new Point(1,2,3);

        assertEquals(p1.subtract(p2).toString(), new Vector(2.0, -1.0, 4.0).toString());
    }

    @Test
    public void shouldMultiplyPointByScalar() {
        Point p = new Point(3,1,7);

        assertEquals(p.multiply(2).toString(), new Point(6.0, 2.0, 14.0).toString());
    }

    @Test
    public void shouldDividePointByScalar() {
        Point p = new Point(3,1,7);

        assertEquals(p.divide(2).toString(), new Point(1.5, 0.5, 3.5).toString());
    }

    @Test
    public void shouldAddVectorToPoint() {
        Point p = new Point(3,1,7);
        Vector v = new Vector(2, 3, 1);

        assertEquals(p.add(v).toString(), new Point(5.0, 4.0, 8.0, 1.0).toString());
    }

    @Test
    public void shouldSubtractVectorFromPoint() {
        Point p = new Point(3,1,7);
        Vector v = new Vector(2, 3, 1);

        assertEquals(p.subtract(v).toString(), new Point(1.0, -2.0, 6.0, 1.0).toString());
    }

    @Test
    public void shouldEqualPoint() {
        Point p1 = new Point(1,2,3);
        Point p2 = new Point(1,2,3);

        assertTrue(p1.equals(p2));
    }

    @Test
    public void shouldGetMinComponents() {
        Point p1 = new Point(4,2,3);
        Point p2 = new Point(1,2,2);

        assertEquals(p1.min(p2).toString(), "(1.0, 2.0, 2.0, 1.0)");
    }

    @Test
    public void shouldGetMaxComponents() {
        Point p1 = new Point(4,2,3);
        Point p2 = new Point(1,2,2);

        assertEquals(p1.max(p2).toString(), "(4.0, 2.0, 3.0, 1.0)");
    }
}

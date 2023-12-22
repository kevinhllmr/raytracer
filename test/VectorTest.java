package test;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import lib.math.Calc;
import lib.math.Point;
import lib.math.Vector;

public class VectorTest {

    @Test
    public void shouldtoStringVectorData() {
        Vector v = new Vector(1,2,3);
        
        assertEquals(v.toString(), "(1.0, 2.0, 3.0, 0.0)");
    }

    @Test
    public void shouldSubtractTwoVectors() {
        Vector v1 = new Vector(3,1,7);
        Vector v2 = new Vector(1,2,3);

        assertEquals(v1.subtract(v2).toString(), new Vector(2.0, -1.0, 4.0).toString());
    }

    @Test
    public void shouldAddTwoVectors() {
        Vector v1 = new Vector(3,1,7);
        Vector v2 = new Vector(1,2,3);

        assertEquals(v1.add(v2).toString(), new Vector(4.0, 3.0, 10.0).toString());
    }

    @Test
    public void shouldNegateVector() {
        Vector v = new Vector(3,1,7);

        assertEquals(v.negate().toString(), new Vector(-3.0, -1.0, -7.0).toString());
    }

    @Test
    public void shouldMultiplyVectorByScalar() {
        Vector v = new Vector(3,1,7);

        assertEquals(v.multiply(2).toString(), new Vector(6.0, 2.0, 14.0).toString());
    }

    @Test
    public void shouldDivideVecotrByScalar() {
        Vector v = new Vector(3,1,7);

        assertEquals(v.divide(2).toString(), new Vector(1.5, 0.5, 3.5).toString());
    }

    @Test
    public void shouldAddVectorToPoint() {
        Point p = new Point(3,1,7);
        Vector v = new Vector(2, 3, 1);

        assertEquals(p.add(v).toString(), new Point(5.0, 4.0, 8.0, 1.0).toString());
    }

    @Test
    public void shouldReturnSquaredMagnitude() {
        Vector v = new Vector(2, 4, 4);

        assertEquals(v.sqrMagnitude(), 36, Calc.EPSILON);
    }

    @Test
    public void shouldReturnMagnitude() {
        Vector v = new Vector(2, 4, 4);

        assertEquals(v.magnitude(), 6, Calc.EPSILON);
    }

    @Test
    public void shouldEqualVector() {
        Vector v1 = new Vector(1,2,3);
        Vector v2 = new Vector(1,2,3);

        assertTrue(v1.equals(v2));
    }  

    @Test
    public void shouldNormalizeVector() {
        Vector v = new Vector(3,9,6);

        assertEquals(v.normalized().magnitude(), 1, Calc.EPSILON);
    }  
    
    @Test
    public void shouldReturnDotProduct() {
        Vector v1 = new Vector(3,2,4);
        Vector v2 = new Vector(1,6,3);

        assertEquals(v1.dot(v2), 27.0, Calc.EPSILON);
    }

    @Test
    public void shouldCrossTwoVectors() {
        Vector v1 = new Vector(3,2,4);
        Vector v2 = new Vector(1,6,3);

        assertEquals(v1.cross(v2).toString(), new Vector(-18, -5, 16).toString());
    }

    @Test
    public void testReflectVectorApproaching45Degrees() {
        Vector v1 = new Vector(1,-1,0);
        Vector v2 = new Vector(0,1,0);

        assertTrue(new Vector(1,1,0).equals(v1.reflect(v2)));
    }

    @Test
    public void testReflectVectorOffSlantedSurface() {
        Vector v1 = new Vector(0, -1, 0);
        Vector v2 = new Vector(0.707011,0.707011,0);

        assertTrue(new Vector(0.99972,-0.00027,0).equals(v1.reflect(v2)));
    }

    @Test
    public void shouldCompareVectorLength() {
        Vector v1 = new Vector(3,2,4);
        Vector v2 = new Vector(7,6,5);

        assertTrue(v1.shorterThan(v2));
        assertTrue(v2.longerThan(v1));
    }
}

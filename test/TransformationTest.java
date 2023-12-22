package test;

import org.junit.jupiter.api.Test;

import lib.math.Matrix;
import lib.math.Point;
import lib.math.Vector;

import static org.junit.Assert.*;

public class TransformationTest {
    
    @Test
    public void testMultiplyByTranslationMatrix() {
        Matrix transform = Matrix.translation(5.0, -3.0, 2.0);
        Point p = new Point(-3, 4, 5);

        assertTrue(new Point(2, 1, 7).equals(transform.multiply(p)));
    }

    @Test
    public void testTransformationMatrixForDefaultOrientation() {
        Point position = new Point(0, 0, 0);
        Point lookAt = new Point(0, 0, -1);
        Vector up = new Vector(0, 1, 0);

        Matrix transform = Matrix.viewTransform(position, lookAt, up);

        assertEquals(Matrix.getIdentity(), transform);
    }

    @Test
    public void testViewTransformationMatrixLookingInPositiveZDirection() {
        Point position = new Point(0, 0, 0);
        Point lookAt = new Point(0, 0, 1);
        Vector up = new Vector(0, 1, 0);

        Matrix transform = Matrix.viewTransform(position, lookAt, up);

        assertEquals(Matrix.scaling(-1, 1, -1), transform);
    }

    @Test
    public void testViewTransformationMovesWorld() {
        Point position = new Point(0, 0, 8);
        Point lookAt = new Point(0, 0, 0);
        Vector up = new Vector(0, 1, 0);

        Matrix transform = Matrix.viewTransform(position, lookAt, up);

        assertEquals(Matrix.translation(0, 0, -8), transform);
    }

    @Test
    public void testArbitraryViewTransformation() {
        Point position = new Point(1, 3, 2);
        Point lookAt = new Point(4, -2, 8);
        Vector up = new Vector(1, 1, 0);

        Matrix transform = Matrix.viewTransform(position, lookAt, up);

        Double[][] data = {
            {-0.50709, 0.50709, 0.67612, -2.36643},
            {0.76772, 0.60609, 0.12122, -2.82843},
            {-0.35857, 0.59761, -0.71714, 0.0},
            {0.0, 0.0, 0.0, 1.0}
        };

        Matrix expected = new Matrix(data);

        assertTrue(transform.equals(expected));
    }
}

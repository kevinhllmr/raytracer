package test;

import org.junit.jupiter.api.Test;

import lib.math.Matrix;
import lib.math.Point;
import lib.math.Vector;
import lib.shapes.Material;
import lib.shapes.Sphere;

import static org.junit.Assert.*;

public class SphereTest {
    
    @Test
    public void testComputeNormalOnTranslatedSphere() {
        Sphere s = new Sphere();
        Matrix transform = Matrix.translation(0, 1, 0);
        s.setTransformation(transform);
        Vector n = s.normalAt(new Point(0, 1.707106, -0.707106));

        assertTrue(new Vector(0, 0.707106, -0.707106).equals(n));
    }

    @Test
    public void testComputeNormalOnTransformedSphere() {
        Sphere s = new Sphere();
        Matrix transform = Matrix.scaling(1, 0.5, 1).multiply(Matrix.rotationZ(0.62831));
        s.setTransformation(transform);
        Vector n = s.normalAt(new Point(0, 0.707106, -0.707106));

        assertTrue(new Vector(0, 0.970142, -0.242536).equals(n));
    }

    @Test
    public void testSphereHasDefaultMaterial() {
        Sphere s = new Sphere();

        assertTrue(s.getMaterial().equals(new Material()));
    }

    @Test
    public void testSphereMayBeAssignedMaterial() {
        Sphere s = new Sphere();
        Material m = new Material();
        m.setAmbient(1.0);
        s.setMaterial(m);

        assertTrue(s.getMaterial().equals(m));
    }
}

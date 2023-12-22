package test;

import org.junit.jupiter.api.Test;

import lib.canvas.Color;
import lib.math.Calc;
import lib.shapes.Material;

import static org.junit.Assert.*;

public class MaterialTest {
    
    @Test
    public void testCreateDefaultMaterial() {
        Material m = new Material();

        assertEquals(0.1, m.getAmbient(), Calc.EPSILON);
        assertEquals(0.9, m.getDiffuse(), Calc.EPSILON);
        assertEquals(0.9, m.getSpecular(), Calc.EPSILON);
        assertEquals(200, m.getShininess());
        assertTrue(m.getColor().equals(new Color(1, 1, 1)));
    }

    @Test
    public void testCreateMaterialClampVariables() {
        Material m = new Material();
        m.setAmbient(-103.0);
        m.setDiffuse(20.0);
        m.setSpecular(1.2);
        m.setShininess(999999);

        assertEquals(0.0, m.getAmbient(), Calc.EPSILON);
        assertEquals(1.0, m.getDiffuse(), Calc.EPSILON);
        assertEquals(1.0, m.getSpecular(), Calc.EPSILON);
        assertEquals(1000, m.getShininess());
    }

    @Test
    public void testEqualMaterial() {
        Material m = new Material();
        Material m2 = new Material();
        Material m3 = new Material();
        
        m3.setAmbient(0.6);

        assertTrue(m.equals(m2));
        assertFalse(m.equals(m3));
    }
}

package test;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import lib.canvas.Color;
import lib.canvas.ColorPalette;
import lib.math.Calc;

public class ColorTest {

    @Test
    public void shouldColorData() {
        Color c = new Color(0.35,0.7,0.4);
        
        assertEquals(c.toString(), "(0.35, 0.7, 0.4, 1.0)");
    }

    @Test
    public void shouldColorData255() {
        Color c = new Color(0.35,0.7,0.4);
        
        assertEquals(c.print255(), "(89, 178, 102, 1.0)");
    }

    @Test
    public void shouldCapMinMaxValue() {
        Color c = new Color(-0.1,2, 0.5);

        assertEquals(c.toString(), "(0.0, 1.0, 0.5, 1.0)");
    }

    @Test
    public void shouldReturnTrueForSimilarColor() {
        Color c1 = new Color(0.35,0.7,0.4);
        Color c2 = new Color(0.35,0.7,0.4);
        Color c3 = new Color(0.5,0.2,0.6);
        
        assertTrue(c1.isSimilar(c2));
        assertFalse(c1.isSimilar(c3));
    }
    
    @Test
    public void shouldReturnSimilarityPercentage() {
        Color c1 = new Color(0.35,0.7,0.4);
        Color c2 = new Color(0.5,0.2,0.6);
        
        assertEquals(ColorPalette.WHITE.getColor().getSimilarity(ColorPalette.BLACK.getColor()), 0, Calc.EPSILON);
        assertEquals(ColorPalette.BLUE.getColor().getSimilarity(ColorPalette.BLUE.getColor()), 100, Calc.EPSILON);
        assertEquals(c1.getSimilarity(c2), 67.72, Calc.EPSILON);
    }

    @Test
    public void testAddingColors() {
        Color c1 = new Color(0.9,0.6,0.75);
        Color c2 = new Color(0.7,0.1,0.25);

        assertTrue(c1.add(c2).equals(new Color(1.0, 0.7, 1.0)));
    }

    @Test
    public void testMultiplyColorByScalar() {
        Color c1 = new Color(0.2,0.3,0.4);

        assertTrue(c1.multiply(2).equals(new Color(0.4, 0.6, 0.8)));
    }

    @Test
    public void testMultiplyColorWithColor() {
        Color c1 = new Color(0.2,0.3,0.4);
        Color c2 = new Color(0.7,0.1,0.25);

        assertTrue(c1.multiply(c2).equals(new Color(0.14, 0.03, 0.1)));
    }
}

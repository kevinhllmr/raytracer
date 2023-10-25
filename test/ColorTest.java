package test;

import org.junit.jupiter.api.Test;

import lib.Color;
import lib.ColorPalette;
import lib.Constants;

import static org.junit.Assert.*;

public class ColorTest {

    @Test
    public void shouldColorData() {
        Color c = new Color(0.35,0.7,0.4);
        
        assertEquals(c.print(), "(0.35, 0.7, 0.4, 1.0)");
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
        
        assertEquals(ColorPalette.WHITE.getColor().getSimilarity(ColorPalette.BLACK.getColor()), 0, Constants.EPSILON);
        assertEquals(ColorPalette.BLUE.getColor().getSimilarity(ColorPalette.BLUE.getColor()), 100, Constants.EPSILON);
        assertEquals(c1.getSimilarity(c2), 67.72, Constants.EPSILON);
    }
}

import org.junit.jupiter.api.Test;

import com.raytracer.math.Calc;

import static org.junit.jupiter.api.Assertions.*;

public class CalcTest {
    
    @Test
    public void testApproxEqual() {
        double a = 0.0000001;
        double b = 0.0;
        double c = 0.001;

        assertTrue(Calc.approxEqual(a, b));
        assertFalse(Calc.approxEqual(a, c));
    }

    @Test
    public void testClamp() {
        double a = 0.05;
        double b = 22.6;
        
        int c = 5;
        int d = 12;

        assertEquals(Calc.clamp(25.0, a, b), 22.6, Calc.EPSILON);
        assertEquals(Calc.clamp(3, c, d), 5, Calc.EPSILON);
    }
}

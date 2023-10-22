package test;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import lib.Point;

public class PointTest {
    
    @Test
    public void shouldEqualPoint() {
        Point p1 = new Point(1,2,3);
        Point p2 = new Point(1,2,3);

        assertTrue(p1.equals(p2));
    }
}

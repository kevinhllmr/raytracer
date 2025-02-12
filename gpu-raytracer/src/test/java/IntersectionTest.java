

import org.junit.jupiter.api.Test;

import com.raytracer.ray.Intersection;
import com.raytracer.ray.IntersectionList;
import com.raytracer.shapes.Sphere;

import static org.junit.jupiter.api.Assertions.*;

public class IntersectionTest {

    @Test
    public void shouldReturnHit() {
        Sphere s = new Sphere();
        Intersection i1 = new Intersection(1, s);
        Intersection i2 = new Intersection(2, s);

        IntersectionList xs = new IntersectionList();
        xs.addIntersection(i1);
        xs.addIntersection(i2);

        assertEquals(i1, xs.hit());
    }

    @Test
    public void shouldReturnHitNegativeT() {
        Sphere s = new Sphere();
        Intersection i1 = new Intersection(-1, s);
        Intersection i2 = new Intersection(1, s);

        IntersectionList xs = new IntersectionList();
        xs.addIntersection(i1);
        xs.addIntersection(i2);

        assertEquals(i2, xs.hit());
    }

    @Test
    public void shouldReturnNoHit() {
        Sphere s = new Sphere();
        Intersection i1 = new Intersection(-2, s);
        Intersection i2 = new Intersection(-1, s);

        IntersectionList xs = new IntersectionList();
        xs.addIntersection(i1);
        xs.addIntersection(i2);

        assertEquals(null, xs.hit());
    }

    @Test
    public void shouldReturnLowestNonNegativeHit() {
        Sphere s = new Sphere();
        Intersection i1 = new Intersection(5, s);
        Intersection i2 = new Intersection(7, s);
        Intersection i3 = new Intersection(-3, s);
        Intersection i4 = new Intersection(2, s);

        IntersectionList xs = new IntersectionList();
        xs.addIntersection(i1);
        xs.addIntersection(i2);
        xs.addIntersection(i3);
        xs.addIntersection(i4);

        assertEquals(i4, xs.hit());
    }
}

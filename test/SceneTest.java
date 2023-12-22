package test;

import org.junit.jupiter.api.Test;

import lib.canvas.Color;
import lib.math.Calc;
import lib.math.Matrix;
import lib.math.Point;
import lib.math.Vector;
import lib.ray.IntersectionList;
import lib.ray.Ray;
import lib.scene.LightSource;
import lib.scene.PointLightSource;
import lib.scene.Scene;
import lib.shapes.Material;
import lib.shapes.Shape;
import lib.shapes.Sphere;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class SceneTest {
    
    @Test
    public void shouldCreateEmptyScene() {
        Scene s = new Scene();
        List<Shape> emptyList = new ArrayList<>();

        assertEquals(emptyList, s.getShapes());
        assertEquals(emptyList, s.getLightSources());
    }

    @Test
    public void shouldAddLightToScene() {
        Scene s = Scene.DefaultScene();
        LightSource light = new PointLightSource(new Point(-10, 10, -10), new Color(1,1,1));

        assertTrue(light.getPosition().equals(s.getLightSources().get(0).getPosition()));
        assertTrue(light.getColor().equals(s.getLightSources().get(0).getColor()));
        assertEquals(light.getIntensity(), s.getLightSources().get(0).getIntensity(), Calc.EPSILON);
    }

    @Test
    public void shouldAddObjectToScene() {
        Scene s = new Scene();
        Sphere sphere = new Sphere();
        s.addShapes(sphere);

        assertEquals(sphere, s.getShapes().get(0));
    }

    @Test
    public void shouldIntersectSceneWithRay() {
        Scene s = Scene.DefaultScene();
        Ray r = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
        IntersectionList xs = s.traceRay(r);

        assertEquals(4, xs.size());
        assertEquals(4, xs.getIntersection(0).t(), Calc.EPSILON); 
        assertEquals(4.5, xs.getIntersection(1).t(), Calc.EPSILON);
        assertEquals(5.5, xs.getIntersection(2).t(), Calc.EPSILON);
        assertEquals(6, xs.getIntersection(3).t(), Calc.EPSILON);

        for(int i = 0; i < xs.size(); i++) {  
           System.out.println(xs.getIntersection(i).t());
        }
    }

    @Test
    public void testDefaultWorld() {
        LightSource light = new PointLightSource(new Point(-10,10,-10), new Color(1,1,1));

        Sphere s1 = new Sphere();
        Material m = new Material();
        m.setColor(new Color(0.8, 1.0, 0.6));
        m.setDiffuse(0.7);
        m.setSpecular(0.2);
        s1.setMaterial(m);

        Sphere s2 = new Sphere();
        s2.setTransformation(Matrix.scaling(0.5));

        Scene scene = Scene.DefaultScene();

        assertTrue(light.equals(scene.getLightSources().get(0)));
    }
}

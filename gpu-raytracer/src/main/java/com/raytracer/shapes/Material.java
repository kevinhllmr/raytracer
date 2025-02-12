package com.raytracer.shapes;

import com.raytracer.canvas.Color;
import com.raytracer.math.Calc;
import com.raytracer.math.Point;
import com.raytracer.math.Vector;
import com.raytracer.scene.LightSource;

public class Material {
    
    private Color color;
    private double ambient;
    private double diffuse;
    private double specular;
    private int shininess;

    public Material() {
        color = new Color(1, 1, 1);
        ambient = 0.1;
        diffuse = 0.9;
        specular = 0.9;
        shininess = 200;
    }

    public Material(Color c) {
        this.color = c;
        ambient = 0.1;
        diffuse = 0.9;
        specular = 0.9;
        shininess = 200;
    }

    public Color phongLighting(LightSource lightSource, Point hit, Vector direction, Vector normal) {
        Color ambientC = color.multiply(ambient);

        Vector lightDirection = lightSource.getPosition().subtract(hit).normalized();
        double cosTheta = normal.dot(lightDirection);
        double maxDiffuse = Math.max(0, cosTheta);
        Color diffuseC = color.multiply(diffuse).multiply(maxDiffuse);

        Vector reflection = normal.multiply(2 * lightDirection.dot(normal)).subtract(lightDirection).normalized();
        double cosAlpha = Math.max(0, direction.dot(reflection));
        double specularFactor = Math.pow(cosAlpha, shininess);
        Color specularC = lightSource.getColor().multiply(specular).multiply(specularFactor);

        Color phongColor = ambientC.add(diffuseC).add(specularC);
        return phongColor;
    }

    public double getAmbient() {
        return ambient;
    }

    public Color getColor() {
        return color;
    }

    public double getDiffuse() {
        return diffuse;
    }

    public int getShininess() {
        return shininess;
    }

    public double getSpecular() {
        return specular;
    }

    public void setAmbient(double ambient) {
        this.ambient = Calc.clamp(ambient, 0.0, 1.0);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setDiffuse(double diffuse) {
        this.diffuse = Calc.clamp(diffuse, 0.0, 1.0);
    }

    public void setShininess(int shininess) {
        this.shininess = Calc.clamp(shininess, 0, 1000);
    }

    public void setSpecular(double specular) {
        this.specular = Calc.clamp(specular, 0.0, 1.0);
    } 

    public boolean equals(Material m2) {
        return (color.equals(m2.color)) && (Calc.approxEqual(ambient, m2.ambient)) && (Calc.approxEqual(diffuse, m2.diffuse)) && (Calc.approxEqual(specular, m2.specular)) && (shininess == m2.shininess);
    }
}

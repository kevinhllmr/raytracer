package com.raytracer.math;

public class Vector {
    
    private double x;
    private double y;
    private double z;
    private double w;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = 0;
    }

    public Vector(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector(Vector v) {
        v.x = x;
        v.y = y;
        v.z = z;
        v.w = w;
    }
    
    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ", " + w + ")";
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public double z() {
        return z;
    }

    public double w() {
        return w;
    }

    public Vector subtract(Vector v2) {
        return new Vector(x - v2.x, y - v2.y, z - v2.z);
    }  
    
    public Vector add(Vector v2) {
        return new Vector(x + v2.x, y + v2.y, z + v2.z);
    }  

    public Vector negate() {
        return new Vector(-x, -y, -z);
    } 

    public Vector multiply(double scalar) {
        return new Vector(x * scalar, y * scalar, z * scalar);
    }

    public Vector divide(double scalar) {
        return new Vector(x / scalar, y / scalar, z / scalar);
    }

    public double sqrMagnitude() {
        return (x*x + y*y + z*z);
    }

    public double magnitude() {
        return Math.sqrt(sqrMagnitude());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vector v2 = (Vector) obj;
        return (Calc.approxEqual(x, v2.x)) && (Calc.approxEqual(y, v2.y)) && (Calc.approxEqual(z, v2.z)) && (Calc.approxEqual(w, v2.w));
    }

    public Vector normalized() {
        double reciprocal = 1/magnitude();
        return multiply(reciprocal);
    }

    public double dot(Vector v2) {
        return (x*v2.x + y*v2.y + z*v2.z);
    }

    public Vector cross(Vector v2) {
        double resultX = y*v2.z - z*v2.y;
        double resultY = z*v2.x - x*v2.z;
        double resultZ = x*v2.y - y*v2.x;

        return new Vector(resultX, resultY, resultZ, 0);
    }

    public Vector reflect(Vector n) {
        return this.subtract(n.multiply(2*this.dot(n)));
    }

    public boolean shorterThan(Vector v2) {
        return sqrMagnitude() < v2.sqrMagnitude();
    }

    public boolean longerThan(Vector v2) {
        return sqrMagnitude() > v2.sqrMagnitude();
    }

    public int getDominantAxis() {
        double absX = Math.abs(x);
        double absY = Math.abs(y);
        double absZ = Math.abs(z);
    
        if (absX >= absY && absX >= absZ) {
            return 0; 
        }
        if (absY >= absX && absY >= absZ) {
            return 1; 
        }
        return 2; 
    }
}

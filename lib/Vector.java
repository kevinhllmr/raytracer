package lib;

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
    
    public String print() {
        return "(" + x + ", " + y + ", " + z + ", " + w + ")";
    }

    public double getW() {
        return w;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Vector subtract(Vector v2) {
        return new Vector(x - v2.x, y - v2.y, z - v2.z, w - v2.w);
    }  
    
    public Vector add(Vector v2) {
        return new Vector(x + v2.x, y + v2.y, z + v2.z, w + v2.w);
    }  

    public Vector negate() {
        return new Vector(-x, -y, -z, -w);
    } 

    public Vector multiply(double scalar) {
        return new Vector(x * scalar, y * scalar, z * scalar, w * scalar);
    }

    public Vector divide(double scalar) {
        return new Vector(x / scalar, y / scalar, z / scalar, w / scalar);
    }

    public double sqrMagnitude() {
        return (x*x + y*y + z*z + w*w);
    }

    public double magnitude() {
        return Math.sqrt(sqrMagnitude());
    }

    public boolean equals(Vector v2) {
        double epsilon = 0.000001d;
        return (Math.abs(x - v2.x) < epsilon) && (Math.abs(y - v2.y) < epsilon) && (Math.abs(z - v2.z) < epsilon) && (Math.abs(w - v2.w) < epsilon);
    }

    public Vector normalized() {
        Scalar reciprocal = new Scalar(1/magnitude());
        return reciprocal.multiply(this);
    }

    public double dot(Vector v2) {
        return (x*v2.x + y*v2.y + z*v2.z + z*v2.z);
    }

    public Vector cross(Vector v2) {
        double resultX = y*v2.z - z*v2.y;
        double resultY = z*v2.x - x*v2.z;
        double resultZ = x*v2.y - y*v2.x;

        return new Vector(resultX, resultY, resultZ, 0);
    }

    public boolean shorterThan(Vector v2) {
        return magnitude() < v2.magnitude();
    }

    public boolean longerThan(Vector v2) {
        return magnitude() > v2.magnitude();
    }
}
package lib;

public class Point {

    private double x;
    private double y;
    private double z;
    private double w;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = 1;
    }

    public Point(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Point(Point p) {
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;
        this.w = p.w;
    }

    public String print() {
        return "(" + x + ", " + y + ", " + z + ", " + w + ")";
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

   public double getW() {
       return w;
   }

    public Vector subtract(Point p2) {
        return new Vector(x - p2.x, y - p2.y, z - p2.z, w - p2.w);
    }  
    
    public Point multiply(double scalar) {
        return new Point(x * scalar, y * scalar, z * scalar, w * scalar);
    }

    public Point divide(double scalar) {
        return new Point(x / scalar, y / scalar, z / scalar, w / scalar);
    }

    public Point add(Vector v) {
        return new Point(x + v.getX(), y + v.getY(), z + v.getZ(), w + v.getW());
    }

    public Point subtract(Vector v) {
        return new Point(x - v.getX(), y - v.getY(), z - v.getZ(), w - v.getW());
    }

    public boolean equals(Point p2) {
        double epsilon = 0.000001d;
        return (Math.abs(x - p2.x) < epsilon) && (Math.abs(y - p2.y) < epsilon) && (Math.abs(z - p2.z) < epsilon) && (Math.abs(w - p2.w) < epsilon);
    }

    public Point min(Point p2) {
        double resultX;
        double resultY;
        double resultZ;
        double resultW;

        resultX = x < p2.x ? x : p2.x;
        resultY = y < p2.y ? x : p2.y;
        resultZ = z < p2.z ? x : p2.z;
        resultW = w < p2.w ? x : p2.w;

        return new Point(resultX, resultY, resultZ, resultW);
    }

    public Point max(Point p2) {
        double resultX;
        double resultY;
        double resultZ;
        double resultW;

        resultX = x > p2.x ? x : p2.x;
        resultY = y > p2.y ? x : p2.y;
        resultZ = z > p2.z ? x : p2.z;
        resultW = w > p2.w ? x : p2.w;

        return new Point(resultX, resultY, resultZ, resultW);
    }
}

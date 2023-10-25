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
        return new Point(x * scalar, y * scalar, z * scalar);
    }

    public Point divide(double scalar) {
        return new Point(x / scalar, y / scalar, z / scalar);
    }

    public Point add(Vector v) {
        return new Point(x + v.getX(), y + v.getY(), z + v.getZ(), w + v.getW());
    }

    public Point subtract(Vector v) {
        return new Point(x - v.getX(), y - v.getY(), z - v.getZ(), w - v.getW());
    }

    public boolean equals(Point p2) {
        return (Math.abs(x - p2.x) < Constants.EPSILON) && (Math.abs(y - p2.y) < Constants.EPSILON) && (Math.abs(z - p2.z) < Constants.EPSILON) && (Math.abs(w - p2.w) < Constants.EPSILON);
    }

    public Point min(Point p2) {
        double resultX;
        double resultY;
        double resultZ;

        resultX = x < p2.x ? x : p2.x;
        resultY = y < p2.y ? x : p2.y;
        resultZ = z < p2.z ? x : p2.z;

        return new Point(resultX, resultY, resultZ);
    }

    public Point max(Point p2) {
        double resultX;
        double resultY;
        double resultZ;

        resultX = x > p2.x ? x : p2.x;
        resultY = y > p2.y ? y : p2.y;
        resultZ = z > p2.z ? z : p2.z;

        return new Point(resultX, resultY, resultZ);
    }
}

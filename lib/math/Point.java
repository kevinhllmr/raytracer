package lib.math;

public class Point {

    private final double x;
    private final double y;
    private final double z;
    private final double w;

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
        return new Point(x + v.x(), y + v.y(), z + v.z(), w + v.w());
    }

    public Point subtract(Vector v) {
        return new Point(x - v.x(), y - v.y(), z - v.z(), w - v.w());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Point p2 = (Point) obj;
        return (Calc.approxEqual(x, p2.x)) && (Calc.approxEqual(y, p2.y)) && (Calc.approxEqual(z, p2.z)) && (Calc.approxEqual(w, p2.w));
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

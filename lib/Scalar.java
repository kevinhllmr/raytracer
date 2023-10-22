package lib;

public class Scalar {
    
    private double scalar;

    public Scalar(double scalar) {
        this.scalar = scalar;
    }

    public double getScalar() {
        return scalar;
    }

    public Point multiply(Point point) {
        return new Point(scalar*point.getX(), scalar*point.getY(), scalar*point.getZ(), scalar*point.getW());
    }

    public Vector multiply(Vector vector) {
        return new Vector(scalar*vector.getX(), scalar*vector.getY(), scalar*vector.getZ(), scalar*vector.getW());
    }
}

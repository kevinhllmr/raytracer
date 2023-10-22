package lib;
public class Color {
    
    private double r;
    private double g;
    private double b;
    private double a;

    public Color() {
        this.r = 0;
        this.g = 0;
        this.b = 0;
        this.a = 1;
    }

    public Color(double r, double g, double b) {
        this.r = Math.max(0.0, Math.min(1.0, r));
        this.g = Math.max(0.0, Math.min(1.0, g));
        this.b = Math.max(0.0, Math.min(1.0, b));
        this.a = 1.0;
    }

    public Color(double r, double g, double b, double a) {
        this.r = Math.max(0.0, Math.min(1.0, r));
        this.g = Math.max(0.0, Math.min(1.0, g));
        this.b = Math.max(0.0, Math.min(1.0, b));
        this.a = Math.max(0.0, Math.min(1.0, a));
    }

    public double getR() {
        return r;
    }

    public double getG() {
        return g;
    }

    public double getB() {
        return b;
    }

    public double getA() {
        return a;
    }

    public double getSimilarity(Color c2) {
        double deltaR = r - c2.r;
        double deltaG = g - c2.g;
        double deltaB = b - c2.b;

        double deltaE = Math.sqrt(deltaR * deltaR + deltaG * deltaG + deltaB * deltaB);

        double maxDeltaE = Math.sqrt(1.0 + 1.0 + 1.0);

        return ((1.0 - (deltaE / maxDeltaE)) * 100.0);
    }
}

package lib.canvas;

import lib.math.Calc;

public class Color {
    
    private final double r;
    private final double g;
    private final double b;
    private final double a;

    public Color() {
        this.r = 0;
        this.g = 0;
        this.b = 0;
        this.a = 1;
    }

    public Color(double r, double g, double b) {
        this.r = Calc.clamp(r, 0.0, 1.0);
        this.g = Calc.clamp(g, 0.0, 1.0);
        this.b = Calc.clamp(b, 0.0, 1.0);
        this.a = 1.0;
    }

    public Color(double r, double g, double b, double a) {
        this.r = Calc.clamp(r, 0.0, 1.0);
        this.g = Calc.clamp(g, 0.0, 1.0);
        this.b = Calc.clamp(b, 0.0, 1.0);
        this.a = Calc.clamp(a, 0.0, 1.0);
    }

    @Override
    public String toString() {
        return "(" + r + ", " + g + ", " + b + ", " + a + ")";
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

    public String print255() {
        return "(" + getR255() + ", " + getG255() + ", " + getB255() + ", " + a + ")";
    }

    public int getR255() {
        return (int) ((r*255)/1);
    }

    public int getG255() {
        return (int) ((g*255)/1);
    }

    public int getB255() {
        return (int) ((b*255)/1);
    }

    public int getA255() {
        return (int) ((a*255)/1);
    }

    public int getRGB() {
        int redInt = (int) (r * 255.0);
        int greenInt = (int) (g * 255.0);
        int blueInt = (int) (b * 255.0);
        int alphaInt = (int) (a * 255.0);

        int rgb = (alphaInt << 24) | (redInt << 16) | (greenInt << 8) | blueInt;
        return rgb;
    }

    public double getSimilarity(Color c2) {
        double deltaR = r - c2.r;
        double deltaG = g - c2.g;
        double deltaB = b - c2.b;

        double deltaE = Math.sqrt(deltaR * deltaR + deltaG * deltaG + deltaB * deltaB);

        double maxDeltaE = Math.sqrt(1.0 + 1.0 + 1.0);

        double perc = ((1.0 - (deltaE / maxDeltaE)) * 100.0);
        double truncated =  Math.floor(perc * 100) / 100;

        return truncated;
    }

    public boolean isSimilar(Color c2) {
        return (r == c2.r && g == c2.g && b == c2.b && a == c2.a);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Color c2 = (Color) obj;
        return (Calc.approxEqual(r, c2.r)) && (Calc.approxEqual(g, c2.g)) && (Calc.approxEqual(b, c2.b)) && (Calc.approxEqual(a, c2.a));
    }


    public Color multiply(double scalar) {
        return new Color(r * scalar, g * scalar, b * scalar);
    }

    public Color multiply(Color c2) {
        return new Color(r*c2.r, g*c2.g, b*c2.b);
    }

    public Color add(Color c2) {
        return new Color(r + c2.r, g + c2.g, b + c2.b);
    }
}

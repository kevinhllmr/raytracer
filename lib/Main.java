package lib;

public class Main {
    
    public static void main(String[] args) {
        Vector v1 = new Vector(1, 2, 0, 0.4);
        Vector v2 = new Vector(2, 2, 0, 7);

        Point p1 = new Point(1, 2, 3, 4);
        Point p2 = new Point(1, 2, 3, 4);

        Color c1 = ColorPalette.BLACK.getColor();
        Color c2 = ColorPalette.GREEN.getColor();

        // System.out.println(v2.sqrMagnitude());
        // System.out.println(v2.normalized().getW());

        // System.out.println(c1.getSimilarity(c2)); 
    }
}

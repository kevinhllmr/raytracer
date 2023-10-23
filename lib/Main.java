package lib;

public class Main {
    
    public static void main(String[] args) {
        Vector v1 = new Vector(1, 2, 0, 0.4);
        Vector v2 = new Vector(2, 2, 0, 7);

        Point p1 = new Point(1, 2, 3, 4);
        Point p2 = new Point(1, 2, 3, 4);

        Color c1 = ColorPalette.BLACK.getColor();
        Color c2 = ColorPalette.BLACK.getColor();

        System.out.println(p1.add(v1).print());
        System.out.println(p1.max(p2).print());

        System.out.println("Länge des Vektors: " + v2.sqrMagnitude());
        System.out.println("W-Komponente des normalisierten Vektors: " + v2.normalized().getW());

        System.out.println("Ähnlichkeit der Farben: " + c1.getSimilarity(c2)); 
        System.out.println(c1.isSimilar(c2));
    }
}

package lib;

public enum ColorPalette {
    BLACK(new Color(0, 0, 0)),
    WHITE(new Color(1, 1, 1)),
    CYAN(new Color(0, 1, 1)),
    BLUE(new Color(0, 0, 1)),
    GREEN(new Color(0, 1, 0)),
    YELLOW(new Color(1, 1, 0)),
    RED(new Color(1, 0, 0)),
    MAGENTA(new Color(1, 0, 1)),
    TRANSPARENT(new Color(0, 0, 0, 0));

    private final Color color;

    private ColorPalette(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}

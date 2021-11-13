package parser.helpers;

public class ShapeDimensions {
    private final Integer radius;
    private final Integer height;
    private final Integer width;
    private final Integer base;
    private final Integer depth;

    public ShapeDimensions(Integer radius, Integer height, Integer width, Integer base, Integer depth) {
        this.radius = radius;
        this.height = height;
        this.width = width;
        this.base = base;
        this.depth = depth;
    }

    public int getRadius() { return radius; }
    public int getHeight() { return height; }
    public int getWidth() { return width; }
    public int getBase() { return base; }
    public int getDepth() { return depth; }
}

package ast;

import parser.helpers.ShapeDimensions;

public class ShapeDec extends Statement {
    private final String shapeName;
    private final String colour;
    private final String shape;
    private final ShapeDimensions shapeDimensions;

    public ShapeDec(String shapeName, String colour, String shape, ShapeDimensions shapeDimensions) {
        this.shapeName = shapeName;
        this.colour = colour;
        this.shape = shape;
        this.shapeDimensions = shapeDimensions;
    }

    public String getColour() {
        return colour;
    }

    public String getShape() {
        return shape;
    }

    public ShapeDimensions getShapeDimensions() {
        return shapeDimensions;
    }

    public String getShapeName() { return shapeName; }

    @Override
    public <T> T accept(YouAnimateVisitor<T> v) {
        return v.visit(this);
    }
}

package ast;

public class CanvasColour extends Node {
    private final String colour;

    public CanvasColour(String colour) {
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }

    @Override
    public <T> T accept(YouAnimateVisitor<T> v) {
        return v.visit(this);
    }
}

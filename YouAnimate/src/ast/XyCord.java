package ast;

public class XyCord extends Node {
    private int xCord = 0;
    private int yCord = 0;
    private int xAdjustment = 0;
    private int yAdjustment = 0;
    private boolean xHasAdjustment = false;
    private boolean yHasAdjustment = false;

    public XyCord(int xCord, int yCord) {
        this.xCord = xCord;
        this.yCord = yCord;
    }

    // Method overloading
    // if there is an adjustment for a coordinate, then use this constructor
    public XyCord(int xCord, int yCord, boolean xHasAdjustment, boolean yHasAdjustment) {
        this.xCord = xCord;
        this.yCord = yCord;
        this.xHasAdjustment = xHasAdjustment;
        this.yHasAdjustment = yHasAdjustment;
    }

    public void setXAdjustment(int adjustment) {
        if (xHasAdjustment) this.xAdjustment = adjustment;
    }

    public void setYAdjustment(int adjustment) {
        if (yHasAdjustment) this.yAdjustment = adjustment;
    }

    public int getXCord() {
        return this.xCord + xAdjustment;
    }

    public int getYCord() {
        return this.yCord + yAdjustment;
    }

    @Override
    public <T> T accept(YouAnimateVisitor<T> v) {
        return v.visit(this);
    }
}

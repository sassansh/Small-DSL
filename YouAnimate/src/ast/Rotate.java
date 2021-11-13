package ast;

public class Rotate extends AnimStatement {
    private final XyCord rotatePosition;

    public Rotate(String objName, XyCord rotatePosition, String timestamp, String duration) {
        this.objName = objName;
        this.rotatePosition = rotatePosition;
        // remove the "s" from the given timestamp and duration
        this.timestamp = timestamp.replaceAll("[^0-9]", "");
        this.duration = Integer.parseInt(duration.replaceAll("[^0-9]", ""));
    }



    public XyCord getRotatePosition() {
        return rotatePosition;
    }

    @Override
    public <T> T accept(YouAnimateVisitor<T> v) {
        return v.visit(this);
    }
}

package ast;

public class Move extends AnimStatement {

    private final XyCord startPosition;
    private final XyCord endPosition;


    public Move(String objName, XyCord startPosition, XyCord endPosition, String timestamp, String duration) {
        this.objName = objName;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        // remove the "s" from the given timestamp and duration
        this.timestamp = timestamp.replaceAll("[^0-9]", "");
        this.duration = Integer.parseInt(duration.replaceAll("[^0-9]", ""));
    }

    public XyCord getStartPosition() {
        return startPosition;
    }

    public XyCord getEndPosition() {
        return endPosition;
    }

    @Override
    public <T> T accept(YouAnimateVisitor<T> v) {
        return v.visit(this);
    }
}

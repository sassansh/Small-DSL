package ast;

public class Stay extends AnimStatement {
    private final XyCord stayPosition;

    public Stay(String objName, XyCord stayPosition, String timestamp, String duration) {
        this.objName = objName;
        this.stayPosition = stayPosition;
        // remove the "s" from the given timestamp and duration
        this.timestamp = timestamp.replaceAll("[^0-9]", "");
        this.duration = Integer.parseInt(duration.replaceAll("[^0-9]", ""));
    }

    public XyCord getStayPosition() {
        return stayPosition;
    }

    @Override
    public <T> T accept(YouAnimateVisitor<T> v) {
        return v.visit(this);
    }
}

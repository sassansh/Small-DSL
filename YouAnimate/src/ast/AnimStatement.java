package ast;

public abstract class AnimStatement extends Statement {
    // abstract class that represents animation statements such as move or stay
    protected String objName;
    protected String timestamp;
    protected int duration;


    public void setObjName(String objName) {
        this.objName = objName;
    }
    public String getObjName() {
        return objName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getDuration() {
        return duration;
    }
}

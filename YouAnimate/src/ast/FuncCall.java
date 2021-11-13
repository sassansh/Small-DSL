package ast;

public class FuncCall extends Statement {
    private final String funcName;
    private final Input input;
    private final Integer repeatNumTimes;
    private  String startTime;

    public FuncCall(String funcName, Input input, Integer repeatNumTimes, String startTime) {
        this.funcName = funcName;
        this.input = input;
        this.repeatNumTimes = repeatNumTimes;
        this.startTime = startTime;
    }

    public String getFuncName() {
        return funcName;
    }

    public Input getInput() {
        return input;
    }

    public Integer getRepeatNumTimes() {
        return repeatNumTimes;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public <T> T accept(YouAnimateVisitor<T> v) {
        return v.visit(this);
    }
}

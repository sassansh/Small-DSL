package ast;

public class Input extends Node {
    private final String var1;
    private final String var2;

    public Input(String var1, String var2) {
        this.var1 = var1;
        this.var2 = var2;
    }

    public String getVar1() {
        return var1;
    }

    public String getVar2() {
        return var2;
    }

    @Override
    public <T> T accept(YouAnimateVisitor<T> v) {
        return v.visit(this);
    }
}

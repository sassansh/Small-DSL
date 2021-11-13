package ast;

import java.util.List;

public class AnimationDef extends Statement {
    private final String funcName;
    private final Input input;
    private final List<AnimStatement> animStatements;

    public AnimationDef(String funcName, Input input, List<AnimStatement> animStatements) {
        this.funcName = funcName;
        this.input = input;
        this.animStatements = animStatements;
    }

    public String getFuncName() {
        return funcName;
    }

    public Input getInput() {
        return input;
    }

    public List<AnimStatement> getAnimStatements() {
        return animStatements;
    }

    @Override
    public <T> T accept(YouAnimateVisitor<T> v) {
        return v.visit(this);
    }
}

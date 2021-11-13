package ast;

import java.util.List;

public class Loop extends Statement {
    private final Integer start;
    private final Integer end;
    private final Integer increment;
    private final List<Statement> loopStatements;

    public Loop(Integer start, Integer end, Integer increment, List<Statement> loopStatements) {
        this.start = start;
        this.end = end;
        this.increment = increment;
        this.loopStatements = loopStatements;
    }

    public Integer getStart() {
        return start;
    }

    public Integer getEnd() {
        return end;
    }

    public Integer getIncrement() {
        return increment;
    }

    public List<Statement> getLoopStatements() {
        return loopStatements;
    }

    @Override
    public <T> T accept(YouAnimateVisitor<T> v) {
        return v.visit(this);
    }
}

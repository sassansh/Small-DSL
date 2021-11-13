package ast;

import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

public class GroupDec extends Statement {
    private final String groupName;
    private final List<TerminalNode> groupedElements;

    public GroupDec(String groupName, List<TerminalNode> groupedElements) {
        this.groupName = groupName;
        this.groupedElements = groupedElements;
    }

    public String getGroupName() { return groupName; }

    public List<TerminalNode> getGroupedElements() { return groupedElements; }

    @Override
    public <T> T accept(YouAnimateVisitor<T> v) {
        return v.visit(this);
    }
}

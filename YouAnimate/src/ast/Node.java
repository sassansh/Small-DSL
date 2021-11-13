package ast;

public abstract class Node {
    abstract public <T> T accept(YouAnimateVisitor<T> v);
}
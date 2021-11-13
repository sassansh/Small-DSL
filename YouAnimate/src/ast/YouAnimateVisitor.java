package ast;

public interface YouAnimateVisitor<T> {
    T visit(ShapeDec s);
    T visit(Program p);
    T visit(CanvasColour cc);
    T visit(XyCord coord);
    T visit(Move m);
    T visit(Stay s);
    T visit(Rotate r);
    T visit(TextDec td);
    T visit(GroupDec gd);
    T visit(Loop l);
    T visit(FuncCall fc);
    T visit(AnimationDef ad);
    T visit(Input i);
}

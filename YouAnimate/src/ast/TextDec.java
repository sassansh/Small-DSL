package ast;

public class TextDec extends Statement {
    private final String textName;
    private final String text;
    private final String colour;
    private final int fontSize;

    public TextDec(String textName, String text, String colour, int fontSize) {
        this.textName = textName;
        this.text = text;
        this.colour = colour;
        this.fontSize = fontSize;
    }

    public String getTextName() { return textName; }

    public String getText() { return text; }

    public String getColour() { return colour; }

    public int getFontSize() { return fontSize; }

    @Override
    public <T> T accept(YouAnimateVisitor<T> v) {
        return v.visit(this);
    }
}

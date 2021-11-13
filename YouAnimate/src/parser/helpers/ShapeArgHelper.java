package parser.helpers;

import org.antlr.v4.runtime.tree.TerminalNode;
import parser.YouAnimateParser;

public class ShapeArgHelper {

    public static ShapeDimensions getShapeDimensions(YouAnimateParser.ShapeDecContext ctx) {
        String shape = ctx.SHAPE().getText();
        switch (shape) {
            case "circle":
            case "sphere":
                if (!hasRadius(ctx)) {
                    throw new RuntimeException("Circle declaration is missing a radius argument");
                }
                return new ShapeDimensions(getDimension(ctx, ctx.RADIUS(0)), null, null, null, null);
            case "rectangle":
                if (!hasHeight(ctx) || !hasWidth(ctx)) {
                    throw new RuntimeException("Rectangle declaration is missing a height and/or width argument");
                }
                return new ShapeDimensions(null, getDimension(ctx, ctx.HEIGHT(0)), getDimension(ctx, ctx.WIDTH(0)), null, null);
            case "square":
                if (!hasHeight(ctx)) {
                    throw new RuntimeException("Square declaration is missing a height argument");
                }
                return new ShapeDimensions(null, getDimension(ctx, ctx.HEIGHT(0)), null, null, null);
            case "cone":
                if (!hasRadius(ctx) || !hasHeight(ctx)) {
                    throw new RuntimeException("Cone declaration is missing a radius and/or height argument");
                }
                return new ShapeDimensions(getDimension(ctx, ctx.RADIUS(0)), getDimension(ctx, ctx.HEIGHT(0)), null, null, null);
            case "triangle":
                if (!hasBase(ctx) || !hasHeight(ctx)) {
                    throw new RuntimeException("Triangle declaration is missing a radius and/or height argument");
                }
                return new ShapeDimensions(null, getDimension(ctx, ctx.HEIGHT(0)), null, getDimension(ctx, ctx.BASE(0)), null);
            case "cube":
                if (!hasHeight(ctx) || !hasWidth(ctx) || !hasDepth(ctx)) {
                    throw new RuntimeException("Cube declaration is missing a height, width and/or depth argument");
                }
                return new ShapeDimensions(null, getDimension(ctx, ctx.HEIGHT(0)), getDimension(ctx, ctx.WIDTH(0)), null, getDimension(ctx, ctx.DEPTH(0)));
            default: break;
        }
        return null;
    }

    private static int getDimension(YouAnimateParser.ShapeDecContext ctx, TerminalNode dimension) {
        int index = ctx.children.indexOf(dimension) + 1;
        int value = Integer.parseInt(ctx.children.get(index).getText());
        return value;
    }

    private static boolean hasRadius(YouAnimateParser.ShapeDecContext ctx) {
        return ctx.RADIUS().size() != 0;
    }

    private static boolean hasHeight(YouAnimateParser.ShapeDecContext ctx) {
        return ctx.HEIGHT().size() != 0;
    }

    private static boolean hasWidth(YouAnimateParser.ShapeDecContext ctx) {
        return ctx.WIDTH().size() != 0;
    }

    private static boolean hasBase(YouAnimateParser.ShapeDecContext ctx) {
        return ctx.BASE().size() != 0;
    }

    private static boolean hasDepth(YouAnimateParser.ShapeDecContext ctx) {
        return ctx.DEPTH().size() != 0;
    }
}

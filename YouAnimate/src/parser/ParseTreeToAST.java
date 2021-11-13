package parser;

import ast.*;
import org.antlr.v4.runtime.tree.TerminalNode;
import parser.helpers.ShapeArgHelper;
import parser.helpers.ShapeDimensions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParseTreeToAST extends YouAnimateParserBaseVisitor<Node>{
    @Override
    public Program visitProgram(YouAnimateParser.ProgramContext ctx) {
        CanvasColour canvasColour = visitCanvasColour(ctx.canvasColour());
        List<Statement> statements = new ArrayList<>();
        for (YouAnimateParser.StatementContext statement : ctx.statement()) {
            statements.add(visitStatement(statement));
        }
        return new Program(canvasColour, statements);
    }

    @Override
    public CanvasColour visitCanvasColour(YouAnimateParser.CanvasColourContext ctx) {
        return new CanvasColour(ctx.COLOUR().getText());
    }

    @Override
    public Statement visitStatement(YouAnimateParser.StatementContext ctx) {
        if(ctx.shapeDec() != null) {
            return visitShapeDec(ctx.shapeDec());
        } else if (ctx.textDec() != null) {
            return visitTextDec(ctx.textDec());
        } else if (ctx.groupDec() != null) {
            return visitGroupDec(ctx.groupDec());
        } else if (ctx.animationDef() != null) {
            return visitAnimationDef(ctx.animationDef());
        } else if (ctx.loop() != null) {
            return visitLoop(ctx.loop());
        }   else if (ctx.funcCall() != null) {
            return visitFuncCall(ctx.funcCall());
        } else if (ctx.animStatement() != null) {
            return visitAnimStatement(ctx.animStatement());
        } else {
            throw new RuntimeException("There was a problem parsing the statements in your program");
        }
    }

    @Override
    public ShapeDec visitShapeDec(YouAnimateParser.ShapeDecContext ctx) {
        ShapeDimensions shapeDimensions = ShapeArgHelper.getShapeDimensions(ctx);
        return new ShapeDec(ctx.TEXT().getText(), ctx.COLOUR().getText(), ctx.SHAPE().getText(), shapeDimensions);
    }

    @Override
    public TextDec visitTextDec(YouAnimateParser.TextDecContext ctx) {
        return new TextDec(ctx.TEXT().getText(), ctx.FREE_TEXT().getText(), ctx.COLOUR().getText(), Integer.parseInt(ctx.NUM().getText()));
    }

    @Override
    public GroupDec visitGroupDec(YouAnimateParser.GroupDecContext ctx) {
        List<TerminalNode> tokens = ctx.TEXT();
        return new GroupDec(tokens.get(0).getText(), tokens.subList(1, tokens.size()));
    }

    @Override
    public XyCord visitXyCord(YouAnimateParser.XyCordContext ctx) {
        if (!ctx.NUM().isEmpty()) {
            if (!ctx.PLUS().isEmpty() && ctx.PLUS().size() == 2) {
                return new XyCord(Integer.parseInt(ctx.NUM().get(0).getText()),
                        Integer.parseInt(ctx.NUM().get(1).getText()),
                        true, true);
            } else if (!ctx.PLUS().isEmpty() && ctx.PLUS().size() == 1 && ctx.PLUS().get(0).getSymbol().getTokenIndex() > ctx.NUM().get(1).getSymbol().getTokenIndex()) {
                return new XyCord(Integer.parseInt(ctx.NUM().get(0).getText()),
                        Integer.parseInt(ctx.NUM().get(1).getText()),
                        false, true);
            } else if (!ctx.PLUS().isEmpty() && ctx.PLUS().size() == 1  && ctx.PLUS().get(0).getSymbol().getTokenIndex() < ctx.NUM().get(1).getSymbol().getTokenIndex()) {
            return new XyCord(Integer.parseInt(ctx.NUM().get(0).getText()),
                    Integer.parseInt(ctx.NUM().get(1).getText()),
                    true, false);
            } else {
                return new XyCord(Integer.parseInt(ctx.NUM().get(0).getText()),
                        Integer.parseInt(ctx.NUM().get(1).getText()),
                        false, false);
            }
        } else {
            throw new RuntimeException("There was an error with parsing coordinates");
        }
    }

    @Override
    public Move visitMove(YouAnimateParser.MoveContext ctx) {
        if (ctx.TEXT().size() == 3 && !ctx.xyCord().isEmpty()) {
            XyCord startPosition = visitXyCord(ctx.xyCord().get(0));
            XyCord endPosition = visitXyCord(ctx.xyCord().get(1));
            String objName = ctx.TEXT().get(0).getText();
            String timestamp = ctx.TEXT().get(1).getText();
            String duration = ctx.TEXT().get(2).getText();
            return new Move(objName, startPosition, endPosition,timestamp, duration);
        } else {
            throw new RuntimeException("There was an error with parsing a move statement");
        }
    }

    @Override
    public Stay visitStay(YouAnimateParser.StayContext ctx) {
        if (ctx.TEXT().size() == 3 && ctx.xyCord() != null) {
            XyCord stayPosition = visitXyCord(ctx.xyCord());
            String objName = ctx.TEXT().get(0).getText();
            String timestamp = ctx.TEXT().get(1).getText();
            String duration = ctx.TEXT().get(2).getText();
            return new Stay(objName, stayPosition, timestamp, duration);
        }else {
            throw new RuntimeException("There was an error with parsing a stay statement");
        }
    }

    @Override
    public Rotate visitRotate(YouAnimateParser.RotateContext ctx) {
        if (ctx.TEXT().size() == 3 && ctx.xyCord() != null) {
            XyCord rotatePosition = visitXyCord(ctx.xyCord());
            String objName = ctx.TEXT().get(0).getText();
            String timestamp = ctx.TEXT().get(1).getText();
            String duration = ctx.TEXT().get(2).getText();
            return new Rotate(objName, rotatePosition, timestamp, duration);
        }else {
            throw new RuntimeException("There was an error with parsing a rotate statement");
        }
    }

    @Override
    public AnimStatement visitAnimStatement(YouAnimateParser.AnimStatementContext ctx) {
        if (ctx.move() != null) {
            return visitMove(ctx.move());
        } else if (ctx.stay() != null) {
            return visitStay(ctx.stay());
        } else if (ctx.rotate() != null) {
            return visitRotate(ctx.rotate());
        } else {
            throw new RuntimeException("There was an error with parsing an animation statement");
        }
    }

    @Override
    public Input visitInput(YouAnimateParser.InputContext ctx) {
        if (ctx.TEXT().size() >= 2) {
            return new Input(ctx.TEXT(0).getText(), ctx.TEXT(1).getText());
        } else {
            throw new RuntimeException("There was an error with the number of input parameters");
        }
    }

    @Override public Loop visitLoop(YouAnimateParser.LoopContext ctx) {
        List<Statement> statements = new ArrayList<>();
        for (YouAnimateParser.LoopStatementContext statement : ctx.loopStatement()) {
            statements.add(visitLoopStatement(statement));
        }
        if (ctx.NUM().size() == 3) {
            Integer start = Integer.parseInt(ctx.NUM(0).getText());
            Integer end = Integer.parseInt(ctx.NUM(1).getText());
            Integer increment = Integer.parseInt(ctx.NUM(2).getText());
            return new Loop(start, end, increment, statements);
        } else {
            throw new RuntimeException("There was an error with parsing a loop");
        }
    }

    @Override
    public Statement visitLoopStatement(YouAnimateParser.LoopStatementContext ctx) {
        if (ctx.funcCall() != null) {
            return visitFuncCall(ctx.funcCall());
        } else if (ctx.animStatement() != null) {
            return visitAnimStatement(ctx.animStatement());
        } else {
            throw new RuntimeException("There was an error with parsing a statement within a loop");
        }
    }

    @Override
    public FuncCall visitFuncCall(YouAnimateParser.FuncCallContext ctx) {
        String funcName = "";
        String startTime = "";
        Input input = null;
        if (ctx.TEXT().size() == 2 && ctx.input() != null) {
            funcName = ctx.TEXT(0).getText();
            input = visitInput(ctx.input());
            startTime = ctx.TEXT(1).getText();
        } else {
            throw new RuntimeException("There was an error with parsing a function call");
        }
        if (ctx.FUNC_START() != null) {
            return new FuncCall(funcName, input, 1, startTime);
        } else if (ctx.FUNC_REPEAT() != null && ctx.NUM() != null) {
            Integer repeatNumTimes = Integer.parseInt(ctx.NUM().getText());
            return new FuncCall(funcName, input, repeatNumTimes, startTime);
        } else {
            throw new RuntimeException("There was an error with parsing a function call");
        }
    }

    @Override public AnimationDef visitAnimationDef(YouAnimateParser.AnimationDefContext ctx) {
        List<AnimStatement> animStatements = new ArrayList<>();
        for (YouAnimateParser.AnimStatementContext statement : ctx.animStatement()) {
            animStatements.add(visitAnimStatement(statement));
        }
        if (ctx.TEXT() != null && ctx.input() != null) {
            String funcName = ctx.TEXT().getText();
            Input input = visitInput(ctx.input());
            return new AnimationDef(funcName, input, animStatements);
        } else {
            throw new RuntimeException("There was an error with parsing an animation definition");
        }
    }
}

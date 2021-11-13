package ui;

import ast.Program;
import ast.YouAnimateEvaluator;
import parser.YouAnimateLexer;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import parser.YouAnimateParser;
import parser.ParseTreeToAST;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        // lexer
        YouAnimateLexer lexer = new YouAnimateLexer(CharStreams.fromFileName("YouAnimate/input.ttxt"));
        for (Token token : lexer.getAllTokens()) {
            System.out.println(token);
        }
        lexer.reset();
        TokenStream tokens = new CommonTokenStream(lexer);
        System.out.println("Done tokenizing");

        // parser
        YouAnimateParser parser = new YouAnimateParser(tokens);
        ParseTreeToAST visitor = new ParseTreeToAST();
        Program parsedProgram = visitor.visitProgram(parser.program());
        System.out.println("Done parsing");

        // evaluator
        PrintWriter writer = new PrintWriter(new FileWriter("YouAnimate/index.html"));
        YouAnimateEvaluator e = new YouAnimateEvaluator(writer);
        e.visit(parsedProgram);
        writer.close();
        System.out.println("Done evaluation");
    }
}

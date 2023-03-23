import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.io.FileInputStream;
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.print("Enter java file path: ");
        Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();
        //
        File file = new File(fileName);
        FileInputStream fis = null;
        fis = new FileInputStream(file);
        //create a char stream that reads from input
        ANTLRInputStream input = new ANTLRInputStream(fis);
        //create a lexer that feeds off input CharStream
        JavaLexer lexer = new JavaLexer(input);
        //create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        //create a parser that fees of the token buffer
        JavaParser parser = new JavaParser(tokens);
        //begin parsing at compilationUnit
        ParseTree tree = parser.compilationUnit();
        // declare a rewriter to rewrite the input stream as required
        TokenStreamRewriter rewriter = new TokenStreamRewriter(tokens);
        //creating object of the AddComments class
        AddComments ct = new AddComments(rewriter);
        // Use a ParseTreeWalker instance to walk the parse tree and apply the listener
        ParseTreeWalker walker = new ParseTreeWalker();
        //Walk the tree created during the parse, trigger callbacks
        walker.walk(ct, tree); //to know how to traverse, pass the class to traverse with "JavaParserBaseListener" in this case
//        ct.visit(tree);

        // Write the modified input to the output file
        FileWriter writer = new FileWriter("output.java");
        writer.write(ct.getModifiedInput());
        writer.close();
        System.out.print("Code Modified and saved in File: output.java ");

    }
}

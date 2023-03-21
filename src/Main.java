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
        ANTLRInputStream input = new ANTLRInputStream(fis);
        JavaLexer lexer = new JavaLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaParser parser = new JavaParser(tokens);
        ParseTree tree = parser.compilationUnit();
        TokenStreamRewriter rewriter = new TokenStreamRewriter(tokens);
        AddComments ct = new AddComments(rewriter);
        // Use a ParseTreeWalker instance to walk the parse tree and apply the listener
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(ct, tree);
//        ct.visit(tree);

        // Write the modified input to the output file
        FileWriter writer = new FileWriter("output.java");
        writer.write(ct.getModifiedInput());
        writer.close();
        System.out.print("Code Modified and saved in File: output.java ");

    }
}

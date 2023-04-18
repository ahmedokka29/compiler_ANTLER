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
//        System.out.print("Enter java file path: ");
//        Scanner sc = new Scanner(System.in);
//        String fileName = sc.nextLine();

        String fileName = "D:\\Ahmed\\GitHub projects\\compiler_ANTLER\\src\\input.java";
        File file = new File(fileName);
        FileInputStream fis = null;

        fis = new FileInputStream(file);
        ANTLRInputStream input = new ANTLRInputStream(fis);
        JavaLexer lexer = new JavaLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaParser parser = new JavaParser(tokens);
        ParseTree tree = parser.compilationUnit();
        TokenStreamRewriter rewriter = new TokenStreamRewriter(tokens);
        blocks blocks = new blocks(rewriter);
        blocks.visit(tree);
        FileWriter writer1 = new FileWriter("D:\\Ahmed\\GitHub projects\\compiler_ANTLER\\blocksResult.java");
        writer1.write(blocks.codeBlocks());
        writer1.close();



        String fileName2 = "D:\\Ahmed\\GitHub projects\\compiler_ANTLER\\blocksResult.java";
        File file2 = new File(fileName2);
        FileInputStream fis2 = null;

        fis2 = new FileInputStream(file2);
        ANTLRInputStream input2 = new ANTLRInputStream(fis2);
        JavaLexer lexer2 = new JavaLexer(input2);
        CommonTokenStream tokens2= new CommonTokenStream(lexer2);
        JavaParser parser2 = new JavaParser(tokens2);
        ParseTree tree2 = parser2.compilationUnit();
        TokenStreamRewriter rewriter2 = new TokenStreamRewriter(tokens2);
        test ct = new test(rewriter2);
        ct.visit(tree2);

        // Write the modified input to the output file
        FileWriter writer2 = new FileWriter("D:\\Ahmed\\GitHub projects\\compiler_ANTLER\\output.java");
        writer2.write(ct.getModifiedInput());
        writer2.close();
        System.out.println("Code Modified and saved in File: output.java");

        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec("java ./output.java");
        try {
            pr.waitFor();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("run output.java");




    }
}

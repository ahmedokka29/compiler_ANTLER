import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
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

        ArrayList<Integer> list = new ArrayList<Integer>();

        File myObj = new File("output.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            list.add(Integer.valueOf(data));
        }
        myReader.close();
        System.out.println(list);

//        fileRead("D:\\Ahmed\\Ahmed_Gam3a\\4th_Computer\\2nd Term\\compiler section\\project\\output.txt");

        String fileName3 = "D:\\Ahmed\\GitHub projects\\compiler_ANTLER\\blocksResult.java";
        File file3 = new File(fileName3);
        FileInputStream fis3 = null;

        fis3 = new FileInputStream(file3);
        ANTLRInputStream input3 = new ANTLRInputStream(fis3);
        JavaLexer lexer3 = new JavaLexer(input3);
        CommonTokenStream tokens3 = new CommonTokenStream(lexer3);
        JavaParser parser3 = new JavaParser(tokens3);
        ParseTree tree3 = parser3.compilationUnit();
        TokenStreamRewriter rewriter3 = new TokenStreamRewriter(tokens3);
        //swap > < with "&lt and &gt" in html file
        for (int i = 0; i < tokens3.getTokens().size(); i++) {
            Token token3 = tokens3.getTokens().get(i);
            if (token3.getText().equals( "<")) {
                rewriter3.replace(token3, "&lt;");
            } else if (token3.getText().equals(">")) {
                rewriter3.replace(token3, "&gt;");
            }
        }
        html htmlExtractor = new html(rewriter3,list);
        htmlExtractor.visit(tree3);
        // Write the modified input to the output file
        FileWriter writer3 = new FileWriter("D:\\Ahmed\\GitHub projects\\compiler_ANTLER\\d3.html");

        writer3.write(htmlExtractor.result());
        writer3.close();
        // running the generated html file automatically using the desired browser
        File htmlFile = new File("D:\\Ahmed\\GitHub projects\\compiler_ANTLER\\d3.html");
        Desktop.getDesktop().browse(htmlFile.toURI());


    }
}

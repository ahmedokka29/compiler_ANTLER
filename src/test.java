import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.TerminalNode;


public class test extends JavaParserBaseVisitor<String> {
    private int currentBlock = 1;
    private TokenStreamRewriter rewriter;

    public test(TokenStreamRewriter rewriter) {
        this.rewriter= rewriter;
    }

    @Override
    public String visitTypeDeclaration(JavaParser.TypeDeclarationContext ctx) {
        Token start = ctx.getStart();
        rewriter.insertBefore(start,"import java.io.FileWriter;\n" +
                "import java.io.IOException;\n");
        return super.visitTypeDeclaration(ctx);
    }

    @Override
    public String visitMethodBody(JavaParser.MethodBodyContext ctx) {
        Token start = ctx.getStart();
        rewriter.insertBefore(start," throws IOException ");
        return super.visitMethodBody(ctx);
    }

    @Override
    public String visitClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        TerminalNode classname = ctx.identifier().IDENTIFIER();
        if (classname.getText().equals("input")) {
            rewriter.replace(classname.getSymbol(), "output");
        }
        return super.visitClassDeclaration(ctx);
    }

    @Override
    public String visitBlock(JavaParser.BlockContext ctx) {
        Token openBrace = ctx.getStart();
        rewriter.insertAfter(openBrace, "// block number " + currentBlock+"\n");
        if(currentBlock==1){
            rewriter.insertAfter(openBrace, "       FileWriter writer = new FileWriter(\"output.txt\");\n");
            Token closeBrace = ctx.getStop();
            rewriter.insertBefore(closeBrace, "writer.close();\n");
        }
        rewriter.insertAfter(openBrace,"        writer.write(\"block number \" +" + currentBlock+"+\" is visited \\n\");\n");
        currentBlock++;

        return super.visitBlock(ctx);
    }


    public String getModifiedInput() {
        return rewriter.getText();
    }
}

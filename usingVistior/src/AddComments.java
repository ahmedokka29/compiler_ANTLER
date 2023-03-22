import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStreamRewriter;

public class AddComments extends JavaParserBaseVisitor<String> {
    private int currentBlock = 1;
    private TokenStreamRewriter rewriter;

    public AddComments(TokenStreamRewriter rewriter) {
        this.rewriter= rewriter;

    }

    @Override
    public String visitBlock(JavaParser.BlockContext ctx) {
        Token openBrace = ctx.getStart();
        rewriter.insertAfter(openBrace, "// block number " + currentBlock);
        currentBlock++;
        return super.visitBlock(ctx);
    }

    public String getModifiedInput() {
        return rewriter.getText();
    }
}

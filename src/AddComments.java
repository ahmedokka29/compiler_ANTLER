import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStreamRewriter;

public class AddComments extends JavaParserBaseListener {
    private int currentBlock = 1;
    private TokenStreamRewriter rewriter;

    public AddComments(TokenStreamRewriter rewriter) {
        this.rewriter = rewriter;
    }

    @Override
    public void enterBlock(JavaParser.BlockContext ctx) {
        Token openBrace = ctx.getStart();
        rewriter.insertAfter(openBrace, "// block number " + currentBlock);
        currentBlock++;
    }

    public String getModifiedInput() {
        return rewriter.getText();
    }
}

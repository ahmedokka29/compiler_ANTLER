import org.antlr.v4.runtime.TokenStreamRewriter;

public class blocks extends JavaParserBaseVisitor<String>{

    private TokenStreamRewriter rewriter;

    public blocks(TokenStreamRewriter rewriter) {
        this.rewriter= rewriter;
    }

    @Override
    public String visitStatement(JavaParser.StatementContext ctx) {
        String s = ctx.getStart().getText();
        s = s.toLowerCase();

        if(s.equals("if") || s.equals("for") || s.equals("while") || s.equals("else if") || s.equals("else")) {
//            System.out.println(ctx.getStart().getText());
//            System.out.println(ctx.getStop().getText());
//            System.out.println(ctx.statement(0).getText().charAt(0));

            if (ctx.statement(0).getText().charAt(0) != '{') {
                rewriter.insertBefore(ctx.statement(0).getStart(), "{");
                rewriter.insertAfter(ctx.getStop(), "}");
            }
        }

        return super.visitStatement(ctx);
    }
    public String codeBlocks() {
        return rewriter.getText();
    }

}

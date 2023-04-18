import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.TerminalNode;

public class blocks extends JavaParserBaseVisitor<String>{

    private TokenStreamRewriter rewriter;
    boolean el;

    public blocks(TokenStreamRewriter rewriter) {
        this.rewriter= rewriter;
    }

    @Override
    public String visitTerminal(TerminalNode node) {
        if (node.getText().equals("else")) el = true;
        if (node.getText().equals("if")) el = false;
        return super.visitTerminal(node);
    }


    @Override
    public String visitStatement(JavaParser.StatementContext ctx) {
        String s = ctx.getStart().getText();
        s = s.toLowerCase();

        if(s.equals("if") || s.equals("for") || s.equals("while") || s.equals("else if")) {
//            System.out.println(ctx.getStart().getText());
//            System.out.println(ctx.getStop().getText());
//            System.out.println(ctx.statement(0).getText().charAt(0));

            if (ctx.statement(0).getText().charAt(0) != '{') {
                rewriter.insertBefore(ctx.statement(0).getStart(), "{");
                rewriter.insertAfter(ctx.getStop(), "\t\n}");
            }
        }else if(el){
            el = false;
            System.out.println(ctx.getText());
            if (ctx.getText().length() >= 3 && ctx.getText().substring(0, 3).equals("if(")) return null;// not else if(
            if (ctx.getText().charAt(0) != '{') {
                rewriter.insertBefore(ctx.getStart(), "{");
                rewriter.insertAfter(ctx.getStop(), "}");
            }

        }

        return super.visitStatement(ctx);
    }
    public String codeBlocks() {
        return rewriter.getText();
    }

}

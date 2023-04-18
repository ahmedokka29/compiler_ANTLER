import com.sun.jdi.IntegerValue;
import org.antlr.v4.runtime.TokenStreamRewriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class html extends JavaParserBaseVisitor<String>{
    HashMap<String,Integer> var = new HashMap<String,Integer>();
    TokenStreamRewriter rewriter;
    int blocksCount = 1;
    ArrayList<Integer> blocks;
    public html(TokenStreamRewriter rewriter , ArrayList<Integer> blocks) {
        this.rewriter = rewriter;
        rewriter.insertBefore(0, "<html> <style>.green {background-color: #22C55E} .red{background-color: #EF4444} .orange{background-color: #FFF82A}</style><body class=green><pre>");
        this.blocks = blocks;
    }

    @Override
    public String visitVariableDeclarator(JavaParser.VariableDeclaratorContext ctx) {

        String varName = ctx.variableDeclaratorId().getText();
        Integer value = Integer.valueOf(ctx.variableInitializer().getText());
        var.put(varName,value);

        return super.visitVariableDeclarator(ctx);
    }


    @Override
    public String visitParExpression(JavaParser.ParExpressionContext ctx) {
//        System.out.println(ctx.expression());
        if(Objects.equals(ctx.expression().bop.getText(), "||") || Objects.equals(ctx.expression().bop.getText(), "&&")){
//            System.out.println(ctx.expression().expression().size());
            Integer left1 = var.get(ctx.expression().expression(0).expression(0).getText());
            Integer right1 = Integer.valueOf(ctx.expression().expression(0).expression(1).getText());
            Integer left2 = var.get(ctx.expression().expression(1).expression(0).getText());
            Integer right2 = Integer.valueOf(ctx.expression().expression(1).expression(1).getText());
            if(left1.equals(right1)){

                String entering = "<span class='" + "orange" + "'>";
                rewriter.insertAfter(ctx.getStart(), entering);
                String leave = "</span>";
                rewriter.insertBefore(ctx.getStop(),leave);
            }else if(!left2.equals(right2))
            {

                String entering = "<span class='" + "red" + "'>";
                rewriter.insertAfter(ctx.getStart(), entering);
                String leave = "</span>";
                rewriter.insertBefore(ctx.getStop(),leave);
            }
        }
        return super.visitParExpression(ctx);
    }

    @Override
    public String visitBlock(JavaParser.BlockContext ctx) {
        String color = this.blocks.contains(blocksCount) ? "green" : "red";
        String entering = "<span class='" + color + "' id='" + blocksCount + "'>";
        rewriter.insertBefore(ctx.getStart(), entering);
        String leave = "</span>";
        rewriter.insertAfter(ctx.getStop(),leave);
        blocksCount++;
        return super.visitBlock(ctx);
    }

    public String result() {
        return rewriter.getText();
    }

}
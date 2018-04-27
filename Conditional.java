import java.util.HashMap;
import java.awt.*;
public class Conditional extends Instruction{
  protected Expression expression;
  protected Instruction i1,i2,eval;

  public Conditional(Expression expr,Instruction i1,Instruction i2){
    expression=expr;
    this.i1=i1;
    this.i2=i2;
  }

  public void execute(Graphics2D g, HashMap<String,Integer> map) throws DeclarationException{
    eval=(expression.value(map)!=0)?i1:i2;
    eval.execute(g,map);
  }
}

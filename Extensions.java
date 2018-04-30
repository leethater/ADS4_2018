import java.awt.Graphics2D;
import java.util.List;
class Extensions{

}
class WhileInstruction extends Instruction{
  protected Expression expression;
  protected Instruction instruction;

  public WhileInstruction(Expression expr,Instruction ins){
    expression=expr;
    instruction=ins;
  }

  public void execute(Graphics2D g, List<Identifier> map) throws Exception{
    while(expression.value(map)!=0) {
      System.out.println("Expr"+ expression.value(map));
      instruction.execute(g,map);
  }}
}


class ForInstruction extends Instruction{
  protected Declaration dec;
  protected Expression condition;
  protected Assignment affectation;
  protected Instruction list;

  public ForInstruction(Declaration dec,Expression cond,Assignment aff, Instruction sup){
    this.dec=dec;
    this.condition=cond;
    affectation=aff;
    list=sup;
  }


  public void execute(Graphics2D g, List<Identifier> map) throws DeclarationException{
    List<Identifier> loc=new List<Identifier>;
    loc.addAll(map);
    dec.execute(g,loc);
    while(condition.value(loc)!=0){
      list.execute(g,loc);
      affectation.execute(g,loc);
    }
  }
}

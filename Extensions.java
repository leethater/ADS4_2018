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


/*class ForInstruction extends Instruction{
  protected Declaration dec;
  protected Expression condition;
  protected Affectation affectation;
  //protected Conditional cond;
  protected Instruction list;

public ForInstruction(Declaration dec,Conditional cond, Instruction sup){
    this.dec=dec;
    this.cond=cond;
    list=sup;
  }

  public ForInstruction(Declaration dec,Expression cond,Affectation aff, Instruction sup){
    this.dec=dec;
    this.condition=cond;
    affectation=aff;
    list=sup;
  }


  public void execute(Graphics2D g, HashMap<String,Integer> map) throws DeclarationException{
    HashMap<String,Integer> m=new HashMap<>();
    m.putAll(map);
    dec.execute(g,m);
    while(condition.value(m)!=0){
      list.execute(g,m);
      affectation.execute(g,m);
    }
  }
}*/

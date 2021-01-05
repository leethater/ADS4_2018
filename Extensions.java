import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;

class WhileInstruction extends Instruction{

  protected Expression expression;
  protected Instruction instruction;

  public WhileInstruction(Expression expr,Instruction ins){
    expression=expr;
    instruction=ins;
  }

  public void execute(Graphics2D g, List<Identifier> map) throws Exception{
    while(expression.value(map)!=0) {
      instruction.execute(g,map);
  }}
}


class ForInstruction extends Instruction{
  protected Declaration dec;
  protected Expression condition;
  protected Assignment affectation;
  protected Instruction list;

  public ForInstruction(Declaration dec,Expression cond, Assignment aff, Instruction sup){
    this.dec=dec;
    this.condition=cond;
    affectation=aff;
    list=sup;
  }


  public void execute(Graphics2D g, List<Identifier> map) throws Exception{
    List<Identifier> m = new ArrayList<Identifier>();
    m.addAll(map);
    dec.execute(g,m);
    while(condition.value(m)!=0){
      list.execute(g,m);
      affectation.execute(g,m);
    }
  }
}

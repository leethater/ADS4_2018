public class LineInstruction extends Instruction{
  protected Token method;
  protected List<Expression> expressions;
  protected ColorToken color;

  public LineInstruction(Token m, List<Expression> e, ColorToken c){
    method=m;
    expressions=e;
    color=c;
  }
}

import java.util.*;
public class ExpressionPlus extends Expression{
  protected Expression expr1,expr2;
  protected Token operator;

  public ExpressionPlus(Expression e1,Expression e2,Token t){
    expr1=e1;
    expr2=e2;
    operator=t;
  }

  public int value(List<Identifier> m) throws DeclarationException{
    int a=expr1.value(m),b=expr2.value(m);
    System.out.println(this + " " + expr1 + " " +operator.getSym()+ " "+ expr2);
    Sym sym=operator.getSym();
    switch(sym){
      case PLUS: return a+b;
      case MINUS: return a-b;
      case MULT: return a*b;
      case DIV: return a/b;
      case LESS: return Math.max(b-a,0);
      case MORE: return Math.min(b-a,0);
      case EQUALS: return (b==a)?1:0;
    }
    return Integer.MIN_VALUE;
  }

  public void checkNset(List<Identifier> map) throws DeclarationException{
    expr1.checkNset(map);
    expr2.checkNset(map);
  }

}

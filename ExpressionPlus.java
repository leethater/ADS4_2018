import java.util.HashMap;
public class ExpressionPlus extends Expression{
  protected Expression expr1,expr2;
  protected Token operator;

  public ExpressionPlus(Expression e1,Expression e2,Token t){
    expr1=e1;
    expr2=e2;
    operator=t;
  }

  public int value(HashMap<String,Integer> m) throws DeclarationException{
    int a=expr1.value(m),b=expr2.value(m);
    Sym sym=operator.getSym();
    switch(sym){
      case PLUS: return a+b;
      case MINUS: return a-b;
      case MULT: return a*b;
      case DIV: return a/b;
    }
    return Integer.MIN_VALUE;
  }

  public void checkNset(HashMap<String,Integer> map) throws DeclarationException{
    expr1.checkNset(map);
    expr2.checkNset(map);
  }

}

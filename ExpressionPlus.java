public class ExpressionPlus extends Expression{
  protected Expression expr1,expr2;
  protected Token operator;

  public ExpressionPlus(Expression e1,Expression e2,Token t){
    expr1=e1;
    expr2=e2;
    operator=t;
  }

  public int value(){
    int a=expr1.value(),b=expr2.value();
    Sym sym=operator.getSym();
    switch(sym){
      case PLUS: return a+b;
      case MINUS: return a-b;
      case MULT: return a*b;
      case DIV: return a/b;
    }
    return Integer.MIN_VALUE;
  }

}

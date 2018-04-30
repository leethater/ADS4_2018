import java.util.List;
abstract class Expression{

  public abstract int value(List<Identifier> m) throws DeclarationException;

  public void checkNset(List<Identifier> map) throws DeclarationException{

  }

  public String toString(){
    return "";
  }

}

class IntExpression extends Expression{
  protected int value;

  public IntExpression(int v){
    this.value=v;
  }

  public int value(List<Identifier> list){
    return value;
  }

  public IntExpression copy(){
    return new IntExpression(value);
  }

  public String toString(){
    return "" + value;
  }
}

class ExpressionPlus extends Expression{
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

}

class Identifier extends Expression{
  protected Sym type;
  protected String name;
  protected Expression e;

  public Identifier(String name){
    this.name=name;
    type=null;
    e=null;
  }

  public Identifier(String name,Expression e,Sym type){
    this.name=name;
    this.e=e;
    this.type=type;
  }

  public int value(List<Identifier> m) throws DeclarationException{
    checkNset(m);
    return e.value(m);
  //  return checkValue(m);
  }

  public Expression getExpression(){
    return e;
  }

  public void setExpression(Expression e){
    this.e=e;
  }

  public Sym getType(){
    return type;
  }

  public void setType(Sym type){
    this.type=type;
  }

  public String getName(){
    return name;
  }


  public Identifier check(List<Identifier> list) throws DeclarationException{
    for(Identifier i:list) {
      if(i.getName().equals(this.name)) return i;
    }
  throw new DeclarationException(this.getName()+" has not been declared");
  }

  public Identifier checkYOLO(List<Identifier> list){
    for(Identifier i:list) {
      if(i.getName().equals(this.name)) return i;
    }
    return null;
  }



  public void checkNset(List<Identifier> list) throws DeclarationException{
    Identifier i=check(list);
    if(i!=null){
      this.e=i.getExpression();
      this.type=i.getType();
    }
  }

}

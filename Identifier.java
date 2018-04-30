import java.util.*;
public class Identifier extends Expression{
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

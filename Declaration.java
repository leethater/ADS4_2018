import java.awt.*;
import java.util.HashMap;
public class Declaration extends Instruction{
  protected Token t;
  protected String name;
  protected Expression expression;

  public Declaration(Token t,String name,Expression e){
    this.t=t;
    this.name=name;
    expression=e;
  }

  public String getName(){
    return name;
  }

  /*public String toString(){
    return id.getName()+"=   "+id.value();
  }*/


  public void execute(Graphics2D g, HashMap<String,Integer> map) throws DeclarationException{
    map.put(name,expression.value(map));
  }
}

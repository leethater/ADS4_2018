import java.awt.*;
public class Declaration extends Instruction{
  protected Token t;
  protected Identifier id;

  public Declaration(Token t,String name,Expression e){
    this.t=t;
    id=new Identifier(name,e.value());
  }

  public String getName(){
    return id.getName();
  }

  public String toString(){
    return id.getName()+"=   "+id.value();
  }
}

import java.awt.*;
public class Declaration extends Instruction{
  protected Token t;
  protected String name;
  protected Expression e;

  public Declaration(Token t,String name,Expression e){
    this.t=t;
    this.name=name;
    this.e=e;
  }

  public void execute(Graphics2D g,HashMap<String,Integer> dec) throws DeclarationException{
      dec.put(name,e.value());
  }

  public String getName(){
    return name;
  }
}

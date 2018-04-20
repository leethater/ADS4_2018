import java.awt.*;
class Identifier extends Expression{
//  protected HashMap<String,Integer>

  public Identifier(){

  }

  public int value(){
    return 0;
  }
}

class Declaration extends Instruction{
  protected Token t;
  protected Identifier i;

  public void execute(Graphics2D g){

  }
}

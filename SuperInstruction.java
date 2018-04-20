import java.util.List;
import java.util.HashMap;
import java.awt.*;
public class SuperInstruction extends Instruction{
  protected Token begin,end;
  protected List<Instruction> instructions;

  public SuperInstruction(Token b,Token e,List<Instruction> l){
    begin=b;
    end=e;
    instructions=l;
  }

  public void execute(Graphics2D g){
    for(Instruction i:instructions) i.execute(g);
  }

  public String toString(){
    String s=begin.toString()+" ";
    for(Instruction i:instructions) s+=i.toString()+" ";
    s+=end.toString();
    return s;
  }
}

import java.util.List;
import java.awt.*;
public class SuperInstruction extends Instruction{
  protected Token begin,end;
  protected List<Instruction> instructions;

  public SuperInstruction(Token b,Token e,List<Instruction> l){
    begin=b;
    end=e;
    instructions=l;
  }

  public void execute(Graphics2D g,HashMap<String,Integer> dec) throws DeclarationException{
    for(Instruction i:instructions) i.execute(g);
    for(Instruction i:instructions){
      if(i instanceof Declaration){
        Declaration d=(Declaration)i;
        dec.remove(d.getName());
      }
    }
  }

  public String toString(){
    String s=begin.toString()+" ";
    for(Instruction i:instructions) s+=i.toString()+" ";
    s+=end.toString();
    return s;
  }
}

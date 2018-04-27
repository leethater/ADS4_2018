import java.awt.Graphics2D;
import java.util.*;
public class SuperInstruction extends Instruction{
  protected Token begin,end;
  protected List<Instruction> instructions;

  public SuperInstruction(Token b,Token e,List<Instruction> l){
    begin=b;
    end=e;
    instructions=l;
  }

  public void execute(Graphics2D g, List<Identifier> map) throws Exception{
    ArrayList<Identifier> local=new ArrayList<>();
    local.addAll(map);
    for(Instruction i:instructions) i.execute(g,local);
    for(Identifier i:map){
      System.out.println(i.getName()+"="+i.getExpression().value(map));
    }
  }

  public String toString(){
    String s=begin.toString()+" ";
    for(Instruction i:instructions) s+=i.toString()+" ";
    s+=end.toString();
    return s;
  }
}

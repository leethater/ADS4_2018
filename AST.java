import java.awt.Graphics2D;
import java.util.*;

public class AST{
  
  protected List<Instruction> list;

  public AST(List<Instruction> list){
    this.list=list;
  }

  public void exec(Graphics2D g) throws Exception{
    List<Identifier> m=new ArrayList<>();
    for(Instruction i:list) i.execute(g,m);
  }
}

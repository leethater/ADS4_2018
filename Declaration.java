import java.awt.Graphics2D;
import java.util.*;
public class Declaration extends Instruction{
  protected Identifier i;

  public Declaration(Sym s,String name,Expression e){
    i=new Identifier(name,e,s);
  }

  public String getName(){
    return i.getName();
  }

  /*public String toString(){
    return id.getName()+"=   "+id.value();
  }*/


  public void execute(Graphics2D g, List<Identifier> map) throws DeclarationException{
    Identifier ident;
      i=new Identifier(i.getName(),new IntExpression(i.getExpression().value(map)),i.getType());
      ident=i.checkYOLO(map);
      if(ident!=null) map.remove(ident);
      map.add(i);
  }
}

import java.awt.Graphics2D;
import java.awt.Color;
import java.util.*;
public class LineInstruction extends Instruction{
  protected Token method;
  protected List<Expression> expressions;
  protected ColorToken color;

  public LineInstruction(Token m, List<Expression> e, ColorToken c){
    method=m;
    expressions=e;
    color=c;
  }

  public void execute(Graphics2D g, List<Identifier> map) throws DeclarationException{
    int[] rgb=color.rgb();
    g.setColor(new Color(rgb[0],rgb[1],rgb[2]));
    //checkNset(map);
    switch(method.getSym()){
      case DRAWR:
      g.drawRect(expressions.get(0).value(map),expressions.get(1).value(map),expressions.get(2).value(map),expressions.get(3).value(map));
      break;

      case DRAWC:
      g.drawOval(expressions.get(0).value(map)-expressions.get(2).value(map),expressions.get(1).value(map)-expressions.get(2).value(map),expressions.get(2).value(map)*2,expressions.get(2).value(map)*2);
      break;

      case FILLC:
      g.fillOval(expressions.get(0).value(map)-expressions.get(2).value(map),expressions.get(1).value(map)-expressions.get(2).value(map),expressions.get(2).value(map)*2,expressions.get(2).value(map)*2);
      break;

      case FILLR:
      g.fillRect(expressions.get(0).value(map),expressions.get(1).value(map),expressions.get(2).value(map),expressions.get(3).value(map));
      break;
    }
  }


/*  public String toString(){
    String s= method.toString()+" ";
    for(Expression e:expressions) s+=e.value(map)+" ";
    s+=color.toString();
    return s;
  }*/

/*  public void checkNset(HashMap<String,Integer> map) throws DeclarationException{
    for(Expression e:expressions) e.checkNset(map);
  }*/
}

import java.util.List;
import java.awt.*;
public class LineInstruction extends Instruction{
  protected Token method;
  protected List<Expression> expressions;
  protected ColorToken color;

  public LineInstruction(Token m, List<Expression> e, ColorToken c){
    method=m;
    expressions=e;
    color=c;
  }

  public void execute(Graphics2D g){
    int[] rgb=color.rgb();
      g.setColor(new Color(rgb[0],rgb[1],rgb[2]));
    switch(method.getSym()){
      case DRAWR:
      g.drawRect(expressions.get(0).value(),expressions.get(1).value(),expressions.get(2).value(),expressions.get(3).value());
      break;

      case DRAWC:
      g.drawOval(expressions.get(0).value(),expressions.get(1).value(),expressions.get(2).value(),expressions.get(2).value());
      break;

      case FILLC:
      g.fillOval(expressions.get(0).value(),expressions.get(1).value(),expressions.get(2).value(),expressions.get(2).value());
      break;

      case FILLR:
      g.fillRect(expressions.get(0).value(),expressions.get(1).value(),expressions.get(2).value(),expressions.get(3).value());
      break;
    }
  }

  public String toString(){
    String s= method.toString()+" ";
    for(Expression e:expressions) s+=e.value()+" ";
    s+=color.toString();
    return s;
  }
}

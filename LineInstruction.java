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

  public void execute(Graphics2D g,HashMap<String,Integer> dec) throws DeclarationException{
    int[] rgb=color.rgb();
    g.setColor(new Color(rgb[0],rgb[1],rgb[2]));
    checkNset(dec);
    switch(method.getSym()){
      case DRAWR:
      g.drawRect(expressions.get(0).value(),expressions.get(1).value(),expressions.get(2).value(),expressions.get(3).value());
      break;

      case DRAWC:
      g.drawOval(expressions.get(0).value()-expressions.get(2).value(),expressions.get(1).value()-expressions.get(2).value(),expressions.get(2).value()*2,expressions.get(2).value()*2);
      break;

      case FILLC:
      g.fillOval(expressions.get(0).value()-expressions.get(2).value(),expressions.get(1).value()-expressions.get(2).value(),expressions.get(2).value()*2,expressions.get(2).value()*2);
      break;

      case FILLR:
      g.fillRect(expressions.get(0).value(),expressions.get(1).value(),expressions.get(2).value(),expressions.get(3).value());
      break;
    }
  }

  public void checkNset(HashMap<String,Integer> dec) throws DeclarationException{
    for(Expression e:expressions){
      if(e instanceof Identifier && e.value()==Integer.MIN_VALUE){
        Identifier i=(Identifier)e;
        if(dec.containsKey(i.getName())){
          i.setValue(dec.get(i.getName()));
        }
        else throw new DeclarationException("Constant "+i.getName()+" has not been declared");
      }
    }
  }

  public String toString(){
    String s= method.toString()+" ";
    for(Expression e:expressions) s+=e.value()+" ";
    s+=color.toString();
    return s;
  }
}

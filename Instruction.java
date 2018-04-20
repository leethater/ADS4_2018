import java.awt.*;
public abstract class Instruction{

  public abstract void execute(Graphics2D g,HashMap<String,Integer> dec) throws DeclarationException;

  public String toString(){
    return "";
  }
}

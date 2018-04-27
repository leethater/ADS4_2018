import java.awt.*;
import java.util.HashMap;
public abstract class Instruction{

  public abstract void execute(Graphics2D g, HashMap<String,Integer> map) throws DeclarationException;

  public String toString(){
    return "";
  }

}

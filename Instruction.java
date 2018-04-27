import java.awt.Graphics2D;
import java.util.*;
public abstract class Instruction{

  public abstract void execute(Graphics2D g, List<Identifier> list) throws Exception;

  public String toString(){
    return "";
  }

}

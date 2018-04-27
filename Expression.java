import java.util.HashMap;
public abstract class Expression{


  public abstract int value(HashMap<String,Integer> m) throws DeclarationException;

  public void checkNset(HashMap<String,Integer> map) throws DeclarationException{

  }

}

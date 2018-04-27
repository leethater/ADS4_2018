import java.util.List;
public abstract class Expression{


  public abstract int value(List<Identifier> m) throws DeclarationException;

  public void checkNset(List<Identifier> map) throws DeclarationException{

  }

  public String toString(){
    return "";
  }

}

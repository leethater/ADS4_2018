import java.util.HashMap;
public class IntExpression extends Expression{
  protected NumberToken token;

  public IntExpression(NumberToken token){
    this.token=token;
  }

  public int value(HashMap<String,Integer> m){
    return token.getValue();
  }
}

import java.util.*;
public class IntExpression extends Expression{
  protected int value;

  public IntExpression(int v){
    this.value=v;
  }

  public int value(List<Identifier> list){
    return value;
  }
}

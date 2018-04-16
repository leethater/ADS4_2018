public class IntExpression extends Expression{
  protected NumberToken token;

  public IntExpression(NumberToken token){
    this.token=token;
  }

  public int value(){
    return token.getValue();
  }
}

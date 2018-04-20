public class Identifier extends Expression{
  protected String name;
  protected int value;

  public Identifier(String name){
    this.name=name;
    value=Integer.MIN_VALUE;
  }

  public int value(){
    return value;
  }

  public String getName(){
    return name;
  }

  public void setValue(int v){
    value=v;
  }
}

import java.util.HashMap;
public class Identifier extends Expression{
  protected String name;
  protected int value;

  public Identifier(String name){
    this.name=name;
    value=Integer.MIN_VALUE;
  }

  public Identifier(String name,int val){
    this.name=name;
    value=val;
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

  public void checkNset(String s,HashMap<String,Integer> dec) throws DeclarationException{
    if(value==Integer.MIN_VALUE){
        if(dec.containsKey(this.getName())){
          this.setValue(dec.get(this.getName()));
          System.out.println(this.getName()+"= "+this.value());
        }
        else throw new DeclarationException(s+". "+"Constant "+this.getName()+" has not been declared");
      }
    }

}

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

  public int value(HashMap<String,Integer> m) throws DeclarationException{
    checkNset(m);
    return value;
  //  return checkValue(m);
  }

  public String getName(){
    return name;
  }

  public void setValue(int v){
    value=v;
  }

  /*public void checkValue(HashMap<String,Integer> dec) throws DeclarationException{
    if(dec.containsKey(this.getName())) return dec.get(this.getName());
    else throw new DeclarationException("Constant "+this.getName()+" has not been declared");
  }*/


  public void checkNset(HashMap<String,Integer> dec) throws DeclarationException{
    if(value==Integer.MIN_VALUE){
        if(dec.containsKey(this.getName())){
          this.setValue(dec.get(this.getName()));
        //  System.out.println(this.getName()+"= "+this.value());
        }
        else throw new DeclarationException("Constant "+this.getName()+" has not been declared");
      }
    }

    public boolean equals(Identifier i){
      return this.name.equals(i.getName());
    }

}

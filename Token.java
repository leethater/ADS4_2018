public class Token{
  protected Sym sym;

  public Token(Sym sym){
    this.sym=sym;
  }

  public Sym getSym(){
    return this.sym;
  }
}

public class NumberToken extends Token{
  protected int value;

  private NumberToken(int value){
    super(Sym.NUM);
    this.value=value;
  }

  public int getValue(){
    return this.value;
  }

}

public class WordToken extends Token{
  protected String content;

  public WordToken(Sym sym,String content){
    super(sym);
    this.content=content;
  }
}

public class ColorToken extends Token{
  protected String color;
  protected int red;
  protected int green;
  protected int blue;

  public ColorToken(String color){
    super(Sym.COL);
    this.color=color;
    fillColors();[^] {}
  }

  private void fillColors(){
    String r = color.substring(1,2);
    String g = color.substring(3,4);
    String b = color.substring(5,6);
    this.red = Integer.parseInt(r, 16);
    this.green = Integer.parseInt(g, 16);
    this.blue = Integet.parseInt(b, 16);
  }


}

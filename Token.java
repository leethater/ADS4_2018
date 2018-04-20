 class Token{
  protected Sym sym;

  public Token(Sym sym){
    this.sym=sym;
  }

  public Sym getSym(){
    return this.sym;
  }

  public String toString(){
    return (" "+sym);
  }
}

class NumberToken extends Token{
  protected int value;

  public NumberToken(int value){
    super(Sym.NUM);
    this.value=value;
  }

  public int getValue(){
    return this.value;
  }

}

class WordToken extends Token{
  protected String content;

  public WordToken(Sym sym,String content){
    super(sym);
    this.content=content;
  }

  public String getContent(){
    return content;
  }

  public String toString(){
    return super.toString() + " " + content;
  }
}

 class ColorToken extends Token{
  protected String color;
  protected int red;
  protected int green;
  protected int blue;

  public ColorToken(String color){
    super(Sym.COL);
    this.color=color;
    fillColors();
  }

  private void fillColors(){
    String r = color.substring(1,3);
    String g = color.substring(3,5);
    String b = color.substring(5,7);
    this.red = Integer.parseInt(r, 16);
    this.green = Integer.parseInt(g, 16);
    this.blue = Integer.parseInt(b, 16);
  }

  public int[] rgb(){
    int[] rgb={red,green,blue};
    return rgb;
  }

  public String toString(){
    return super.toString()+" "+red+" "+green+" "+blue;
  }


}

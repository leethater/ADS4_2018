public class Token{
  protected Sym sym;

  public Token(Sym sym){
    this.sym=sym;
  }
}

public class NumberToken extends Token{
  protected int value;

  private NumberToken(int value){
    super(Sym.NUM);
    this.value=value;
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

  public ColorToken(String color){
    super(Sym.CCOL);
    this.color=color;
  }
}

public class OpToken extends Token{
  protected String operator;

  public OpToken(String op){
    operator=op;
  }
}

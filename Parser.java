import java.io.*;
import java.util.*;

class Parser{
  protected LookAhead1 reader;

  public Parser(LookAhead1 r){
    reader=r;
  }

  /*programme → suite-instructions
  instruction → Begin suite-instructions End |
  DrawCircle ( expr , expr , expr , couleur ) |
  FillCircle ( expr , expr , expr , couleur ) |
   DrawRect ( expr , expr , expr , expr , couleur ) |
    FillRect ( expr , expr , expr , expr , couleur )

  suite-instructions → instruction ; suite-instructions | ε
  expr → nombre | ( expr opérateur expr )
  */



public AST progNotTerm() throws Exception{
  List<Instruction> list=instructionsList();
  return new AST(list);
}

public Instruction instruction(){
  Instruction i;
  if(reader.check(Sym.BEGIN)){
    Token b=reader.getCurrent();
    reader.eat(Sym.BEGIN);
    List<Instruction> l=instructionsList();
    Token b2=reader.getCurrent();
    reader.eat(Sym.END);
    i=new SuperInstruction(b,b2,l);
  }
  else{
    boolean b=false;
    Token b1=reader.getCurrent();
    List<Expression> l=new ArrayList<Expression>();
    if(reader.check(Sym.DRAWC)) reader.eat(Sym.DRAWC);
    else if(reader.check(Sym.FILLC)) reader.eat(Sym.FILLC);
    else if(reader.check(Sym.DRAWR)){
      reader.eat(Sym.DRAWR);
      b=true;
    }
    else if(reader.check(Sym.FILLR)){
      reader.eat(Sym.FILLR);
      b=true;
    }
    reader.eat(Sym.LPAR);
    l.add(expr());
    reader.eat(Sym.COMMA);
    l.add(expr());
    reader.eat(Sym.COMMA);
    l.add(expr());
    reader.eat(Sym.COMMA);
    if(b){
      l.add(expr());
      reader.eat(Sym.COMMA);
    }
    Token b2=reader.getCurrent();
    reader.eat(Sym.COL);
    ColorToken b3=(ColorToken)b2;
    reader.eat(Sym.RPAR);
    i=new LineInstruction(b1,l,b3);
  }
  return i;
}


public List<Instruction> instructionsList(List<Instruction> l){
  Instruction i=this.instruction();
  l.add(i);
  reader.eat(Sym.SEMIC);
  if(!reader.isEmpty()) instructionsList(l);
  return l;
}

public Expression expr() throws Exception{
  Expression e;
  if(reader.check(Sym.NUM)){
    Token t=reader.getCurrent();
    reader.eat(Sym.NUM);
    NumberToken n=(NumberToken)t;
    e=new IntExpression(n);
  else{
    reader.eat(Sym.LPAR);
    Expression e1=expr();
    Token o=reader.getCurrent();
    switch(o.getSym()){
      case Sym.PLUS: reader.eat(Sym.PLUS);
        break;
      case Sym.MINUS: reader.eat(Sym.MINUS);
        break;
      case Sym.MULT:reader.eat(Sym.MULT);
        break;
      case Sym.DIV: reader.eat(Sym.DIV);
        break;
      default: throw new ParserException(reader.getLexer().getPosition()+"  Not valid operator encountered !");
    }
    Expression e2=expr();
    reader.eat(Sym.RPAR);
    e=new ExpressionPlus(e1,e2,o);
}
return e;
}

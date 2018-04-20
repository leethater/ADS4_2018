import java.io.*;
import java.util.*;

class Parser{
  protected LookAhead1 reader;

  public Parser(LookAhead1 r){
    reader=r;
  }

public AST progNotTerm() throws Exception{
  HashMap<String,Integer> declarations=new HashMap<String,Integer>();
  List<Instruction> list=instructionsList(new ArrayList<Instruction>(),declarations,null);
  if(!reader.isEmpty()) throw new ParserException(reader.getLexer().getPosition()+"  Not valid end of file");
  return new AST(list);
}

public Instruction instruction(HashMap<String,Integer> dec,HashMap<String,Integer> loc) throws Exception{
  Instruction i;
  if(reader.check(Sym.BEGIN)){
    HashMap<String,Integer> local=new HashMap<String,Integer>();
    Token b=reader.getCurrent();
    reader.eat(Sym.BEGIN);
    List<Instruction> l=instructionsList(new ArrayList<Instruction>(),dec,local);
    Token b2=reader.getCurrent();
    reader.eat(Sym.END);
    i=new SuperInstruction(b,b2,l);
  }
  else{
    boolean b=false;
    Token b1=reader.getCurrent();
    if(reader.check(Sym.CONST)){
      reader.eat(Sym.CONST);
      String s=((WordToken)reader.getCurrent()).getContent();
      reader.eat(Sym.IDENT);
      reader.eat(Sym.EQUALS);
      Expression e=expr(dec,loc);
      i=new Declaration(b1,s,e);
      if(loc!=null) loc.put(s,e.value());
      else dec.put(s,e.value());
    }
    else{
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
      l.add(expr(dec,loc));
      reader.eat(Sym.COMMA);
      l.add(expr(dec,loc));
      reader.eat(Sym.COMMA);
      l.add(expr(dec,loc));
      reader.eat(Sym.COMMA);
      if(b){
        l.add(expr(dec,loc));
        reader.eat(Sym.COMMA);
      }
      Token b2=reader.getCurrent();
      reader.eat(Sym.COL);
      ColorToken b3=(ColorToken)b2;
      reader.eat(Sym.RPAR);
      i=new LineInstruction(b1,l,b3);
    }
  }
  return i;
}


public List<Instruction> instructionsList(List<Instruction> l,HashMap<String,Integer> dec,HashMap<String,Integer> loc) throws Exception{
  Instruction i=this.instruction(dec,loc);
  l.add(i);
  reader.eat(Sym.SEMIC);
  if(!reader.isEmpty() && !reader.check(Sym.END)){
    instructionsList(l,dec,loc);
  }
  return l;
}

public Expression expr(HashMap<String,Integer> dec,HashMap<String,Integer> loc) throws Exception{
  Expression e;
  if(reader.check(Sym.NUM)){
    Token t=reader.getCurrent();
    reader.eat(Sym.NUM);
    NumberToken n=(NumberToken)t;
    e=new IntExpression(n);
  }
  else if(reader.check(Sym.IDENT)){
    String key=((WordToken)reader.getCurrent()).getContent();
    reader.eat(Sym.IDENT);
    e=new Identifier(key);
    if(loc!=null){
      try{
        ((Identifier)e).checkNset(reader.getLexer().getPosition(),loc);
      }
      catch(DeclarationException exc){
        ((Identifier)e).checkNset(reader.getLexer().getPosition(),dec);
      }
    }
    else ((Identifier)e).checkNset(reader.getLexer().getPosition(),dec);
  }
  else{
    reader.eat(Sym.LPAR);
    Expression e1=expr(dec,loc);
    Token o=reader.getCurrent();
    switch(o.getSym()){
      case PLUS: reader.eat(Sym.PLUS);
        break;
      case MINUS: reader.eat(Sym.MINUS);
        break;
      case MULT:reader.eat(Sym.MULT);
        break;
      case DIV: reader.eat(Sym.DIV);
        break;
      default: throw new ParserException(reader.getLexer().getPosition()+"  Not valid operator encountered !");
    }
    Expression e2=expr(dec,loc);
    reader.eat(Sym.RPAR);
    e=new ExpressionPlus(e1,e2,o);
}
return e;
}
}

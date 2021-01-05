import java.io.*;
import java.util.*;

class Parser{
  
  protected LookAhead1 reader;

  public Parser(LookAhead1 r){
    reader=r;
  }


public AST progNotTerm() throws Exception{
  List<Instruction> list=instructionsList(new ArrayList<Instruction>());
  if(!reader.isEmpty()) throw new ParserException(reader.getLexer().getPosition()+"  Not valid end of file");
  return new AST(list);
}

public Instruction instruction() throws Exception{
  Instruction i;
  if(reader.check(Sym.BEGIN)){
    reader.eat(Sym.BEGIN);
    List<Instruction> l=instructionsList(new ArrayList<Instruction>());
    reader.eat(Sym.END);
    i=new SuperInstruction(l);
  }
  else{
    boolean b=false;
    Token b1=reader.getCurrent();
    if(reader.check(Sym.CONST)){
      reader.eat(Sym.CONST);
      String s=((WordToken)reader.getCurrent()).getContent();
      reader.eat(Sym.IDENT);
      reader.eat(Sym.EQUALS);
      Expression e=expr();
      //useless
      i=new Declaration(Sym.CONST,s,e);
  }
  else if(reader.check(Sym.VAR)){
    reader.eat(Sym.VAR);
    String s=((WordToken)reader.getCurrent()).getContent();
    reader.eat(Sym.IDENT);
    reader.eat(Sym.EQUALS);
    Expression e=expr();
    i=new Declaration(Sym.VAR,s,e);
  }
  else if(reader.check(Sym.IDENT)){
    String s=((WordToken)reader.getCurrent()).getContent();
    reader.eat(Sym.IDENT);
    reader.eat(Sym.EQUALS);
    Expression e2=expr();
    i=new Assignment(b1.getSym(),s,e2);
    }
    else if(reader.check(Sym.WHILE)){
      reader.eat(Sym.WHILE);
      Expression e = expr();
      reader.eat(Sym.DO);
      Instruction i2 = instruction();
      i = new WhileInstruction(e,i2);
    }
  else if(reader.check(Sym.IF)){
      reader.eat(Sym.IF);
      Expression e=expr();
      reader.eat(Sym.THEN);
      Instruction i1=instruction();
      Instruction i2;
      if(reader.check(Sym.ELSE)){
        reader.eat(Sym.ELSE);
        i2=instruction();
      }
      else{
        reader.eat(Sym.FI);
        i2=null;
      }
      i=new Conditional(e,i1,i2);
  }
  else if(reader.check(Sym.FOR)){
    reader.eat(Sym.FOR);
    reader.eat(Sym.LPAR);
    reader.eat(Sym.VAR);
    String s=((WordToken)reader.getCurrent()).getContent();
    reader.eat(Sym.IDENT);
    reader.eat(Sym.EQUALS);
    Expression e=expr();
    Declaration dec = new Declaration(Sym.VAR,s,e);
    reader.eat(Sym.COMMA);
    Expression cond = expr();
    reader.eat(Sym.COMMA);
    String s1=((WordToken)reader.getCurrent()).getContent();
    reader.eat(Sym.IDENT);
    reader.eat(Sym.EQUALS);
    Expression e2=expr();
    Assignment affect = new Assignment(Sym.VAR,s1,e2);
    reader.eat(Sym.RPAR);
    reader.eat(Sym.DO);
    Instruction l = instruction();
    i = new ForInstruction(dec, cond, affect, l);

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
  }
  return i;
}

public List<Instruction> instructionsList(List<Instruction> l) throws Exception{
  Instruction i=this.instruction();
  l.add(i);
  reader.eat(Sym.SEMIC);
  if(!reader.isEmpty() && !reader.check(Sym.END)){
    instructionsList(l);
  }
  return l;
}

public Expression expr() throws Exception{
  Expression e;
  if(reader.check(Sym.NUM)){
    Token t=reader.getCurrent();
    reader.eat(Sym.NUM);
    NumberToken n=(NumberToken)t;
    e=new IntExpression(n.getValue());
  }
  else if(reader.check(Sym.IDENT)){
    String key=((WordToken)reader.getCurrent()).getContent();
    reader.eat(Sym.IDENT);
    e=new Identifier(key);
  }
  else{
    reader.eat(Sym.LPAR);
    Expression e1=expr();
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
      case LESS: reader.eat(Sym.LESS);
        break;
      case MORE: reader.eat(Sym.MORE);
        break;
      case EQUALEQUALS: reader.eat(Sym.EQUALEQUALS);
        break;
      default: throw new ParserException(reader.getLexer().getPosition()+"  Not valid operator encountered !");
    }
    Expression e2=expr();
    reader.eat(Sym.RPAR);
    e=new ExpressionPlus(e1,e2,o);
}
return e;
}

}

import java.awt.Graphics2D;
import java.util.*;
import java.awt.Color;
 abstract class Instruction{

  public abstract void execute(Graphics2D g, List<Identifier> list) throws Exception;

}

class LineInstruction extends Instruction{
 protected Token method;
 protected List<Expression> expressions;
 protected ColorToken color;

 public LineInstruction(Token m, List<Expression> e, ColorToken c){
   method=m;
   expressions=e;
   color=c;
 }

 public void execute(Graphics2D g, List<Identifier> map) throws DeclarationException{
   int[] rgb=color.rgb();
   g.setColor(new Color(rgb[0],rgb[1],rgb[2]));
   switch(method.getSym()){
     case DRAWR:
     g.drawRect(expressions.get(0).value(map),expressions.get(1).value(map),expressions.get(2).value(map),expressions.get(3).value(map));
     break;

     case DRAWC:
     g.drawOval(expressions.get(0).value(map)-expressions.get(2).value(map),expressions.get(1).value(map)-expressions.get(2).value(map),expressions.get(2).value(map)*2,expressions.get(2).value(map)*2);
     break;

     case FILLC:
     g.fillOval(expressions.get(0).value(map)-expressions.get(2).value(map),expressions.get(1).value(map)-expressions.get(2).value(map),expressions.get(2).value(map)*2,expressions.get(2).value(map)*2);
     break;

     case FILLR:
     g.fillRect(expressions.get(0).value(map),expressions.get(1).value(map),expressions.get(2).value(map),expressions.get(3).value(map));
     break;
   }
 }

 class SuperInstruction extends Instruction{
   protected Token begin,end;
   protected List<Instruction> instructions;

   public SuperInstruction(Token b,Token e,List<Instruction> l){
     begin=b;
     end=e;
     instructions=l;
   }

   public void execute(Graphics2D g, List<Identifier> map) throws Exception{
     ArrayList<Identifier> local=new ArrayList<>();
     local.addAll(map);
     for(Instruction i:instructions) i.execute(g,local);
   }
 }


class Declaration extends Instruction{
 protected Identifier i;

 public Declaration(Sym s,String name,Expression e){
   i=new Identifier(name,e,s);
 }

 public String getName(){
   return i.getName();
 }

 public void execute(Graphics2D g, List<Identifier> map) throws DeclarationException{
   Identifier ident;
     i=new Identifier(i.getName(),new IntExpression(i.getExpression().value(map)),i.getType());
     ident=i.checkYOLO(map);
     if(ident!=null) map.remove(ident);
     map.add(i);
 }
}

class Assignment extends Instruction{
  protected Identifier ident;

  public Assignment(Sym sym, String name, Expression e){
    ident=new Identifier(name,e,sym);
  }

  public void execute(Graphics2D g, List<Identifier> map) throws DeclarationException,AssignmentException{
    Identifier i;
    if((i=ident.check(map))!=null){
      i.setExpression(new IntExpression(ident.getExpression().value(map)));
      ident.setType(i.getType());
      if(ident.getType()!=Sym.VAR) throw new AssignmentException(ident.getName()+" is not a variable");
    }
  }
}

class Conditional extends Instruction{
  protected Expression expression;
  protected Instruction i1,i2,eval;

  public Conditional(Expression expr,Instruction i1,Instruction i2){
    expression=expr;
    this.i1=i1;
    this.i2=i2;
  }

  public void execute(Graphics2D g, List<Identifier> map) throws Exception{
    eval=(i2==null)?i1:(expression.value(map)!=0)?i1:i2;
    eval.execute(g,map);
  }
}

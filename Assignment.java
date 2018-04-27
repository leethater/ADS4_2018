import java.awt.Graphics2D;
import java.util.List;
public class Assignment extends Instruction{
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

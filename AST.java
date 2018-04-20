import java.util.List;
import java.awt.*;
public class AST{
  protected List<Instruction> list;

  public AST(List<Instruction> list){
    this.list=list;
  }

  public void exec(Graphics2D g){
    for(Instruction i:list) i.execute(g);
  }

  public String toString(){
    String s="";
    for(Instruction i:list) s+=i.toString();
      return s;
    }

  /*public void exec(Graphics2D g){
    List<Declaration> constants=new ArrayList<>();
    for(Instruction i:list){
      if(i instanceof LineInstruction){
      /*
      -Dans l'ast on doit créer une instruction et une expression dans le cas d'une constante
      -Si une constante est utilisée dans l'instruction, on verifie qu'elle est dans la liste des constantes déjà déclarées
      -On récupère les évaluations entières des expressions(list de int) et on crée un objet Color avec les 3 valeurs rgb du color token
      -On fait un switch sur le symbole du token et on appelle la fonction java qu'il faut avec les paramètres qu'il defaul
      -On affiche l'objet obtenu avec le graphics 2D, à vérifier
      else{
        -On récupère la suite d'instructions et on rappelle exec dessus, il faudrait surement faire une fontion d'execution dans instruction
      }
      else if(i instanceof Declaration){
        constants.add((Declaration)i);
      }
    }
  }*/

}

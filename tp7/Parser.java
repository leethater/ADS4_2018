import java.io.*;
import java.util.*;

class Parser {
	/*
	 * grammaire equivalente LL1
	 * S -> prologue < A $
	 * A -> mot Atr ASuite
	 * Atr -> epsilon | mot = string Atr
	 * ASuite -> /> | > < E
	 * E -> / mot > | A < E
	 */
    protected LookAhead1 reader;

    public Parser(LookAhead1 r) {
	      reader=r;
    }


    // Completez ici
    public Arbre nonterm_S() throws Exception {
      reader.eat(Sym.PROLOGUE);
      reader.eat(Sym.LCHEVRON);
      Arbre a=nonterm_A();
      reader.eat(Sym.EOF);
      return a;
    }

    public Arbre nonterm_A() throws Exception {
      String name=reader.getValue();
      reader.eat(Sym.MOT);
      Map<String,String> m=nonterm_Atr(new HashMap<String,String>());
      List<Arbre> l=nonterm_ASuite();
      return new Arbre(name,m,l);
    }

    public Map<String,String> nonterm_Atr(Map<String,String> m) throws Exception {
      if(reader.check(Sym.MOT)){
        String s1=reader.getValue();
        reader.eat(Sym.MOT);
        reader.eat(Sym.EQ);
        String s2=reader.getValue();
        reader.eat(Sym.STRING);
        m.put(s1,s2);
        nonterm_Atr(m);
      }
      return m;
    }

    public List<Arbre> nonterm_ASuite() throws Exception {
      List<Arbre> l=new ArrayList<Arbre>();
      if(reader.check(Sym.SLASH)){
        reader.eat(Sym.SLASH);
        reader.eat(Sym.RCHEVRON);
      }
      else{
        reader.eat(Sym.RCHEVRON);
        reader.eat(Sym.LCHEVRON);
        nonterm_E(l);
      }
      return l;
    }


    public List<Arbre> nonterm_E(List<Arbre> l) throws Exception {
      if(reader.check(Sym.SLASH)){
        reader.eat(Sym.SLASH);
        reader.eat(Sym.MOT);
        reader.eat(Sym.RCHEVRON);
      }
      else{
        l.add(nonterm_A());
        reader.eat(Sym.LCHEVRON);
        nonterm_E(l);
      }
      return l;
    }
}

import java.util.*;

class Arbre { //Syntaxe abstraite pour les cartes OSM
	private String name;
	private Map<String,String> attributes;
	private List<Arbre> elements;

	public Arbre(String name,  Map<String,String> attributes, List<Arbre> elements){
		this.name = name ;
		this.attributes = attributes ;
		this.elements = elements ;
	}

	public String toString(){
		return "Arbre: "+name+"\nAttributes: "+attributes+"\nElements:\n"+ elements+"\n";
	}

	public
}

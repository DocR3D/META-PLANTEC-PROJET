package MetaModel;

import java.util.ArrayList;

public class Modele extends Type {

	ArrayList<NamedElement> entites; //TODO Modifier le type ? 
	
	
	public Modele(String nom, int id) {
		super(nom,id);
		this.entites = new ArrayList<NamedElement>();
	}


	@Override
	public void accept(Visitor v) {
		v.visitModele(this);	
	}


	public ArrayList<NamedElement> valeur() {
		return entites;
	}
	
	public void addType(Type t) {
		entites.add(t);
	}
	
	
	

}

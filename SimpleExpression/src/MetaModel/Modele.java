package MetaModel;

import java.util.ArrayList;

public class Modele extends Type {

	ArrayList<Type> entites;
	
	
	public Modele(String nom, int id) {
		super(nom,id);
		this.entites = new ArrayList<Type>();
	}


	@Override
	public void accept(Visitor v) {
		v.visitModele(this);	
	}


	public ArrayList<Type> valeur() {
		return entites;
	}
	
	
	

}

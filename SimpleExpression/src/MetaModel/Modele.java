package MetaModel;

import java.util.ArrayList;

public class Modele extends NamedElement {

	ArrayList<Entite> entites;
	
	
	public Modele(String nom, int id) {
		super(nom,id);
		this.entites = new ArrayList<Entite>();
	}


	@Override
	public void accept(Visitor v) {
		v.visitModele(this);
		
	}


	public ArrayList<Entite> valeur() {
		return entites;
	}
	
	
	

}

package MetaModel;

import java.util.ArrayList;

public class Entite extends Type {
	
	ArrayList<Type> attributs;

	public Entite(java.lang.String nom, int id) {
		super(nom, id);
		this.attributs = new ArrayList<Type>();
	}
	
	@Override
	public void accept(Visitor v) {
		v.visitEntite(this);
	}
	
	public ArrayList<Type> valeur() {
		return attributs;
	}

}

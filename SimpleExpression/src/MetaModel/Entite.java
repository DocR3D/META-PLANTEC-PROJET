package MetaModel;

import java.util.ArrayList;

public class Entite extends NamedElement {
	
	ArrayList<Attribut> attributs;

	public Entite(java.lang.String nom, int id) {
		super(nom, id);
		this.attributs = new ArrayList<Attribut>();
	}
	
	@Override
	public void accept(Visitor v) {
		v.visitEntite(this);
	}
	
	public ArrayList<Attribut> valeur() {
		return attributs;
	}

}

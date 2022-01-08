package MetaModel;

import java.util.ArrayList;

public class Entite extends Type {
	
	ArrayList<NamedElement> attributs;

	public Entite(String nom, int id) {
		super(nom, id);
		this.attributs = new ArrayList<NamedElement>();
	}
	
	@Override
	public void accept(Visitor v) {
		v.visitEntite(this);
	}
	
	public ArrayList<NamedElement> valeur() {
		return attributs;
	}
	
	public void addType(Attribut unAttribut) {
		attributs.add(unAttribut);
	}

}

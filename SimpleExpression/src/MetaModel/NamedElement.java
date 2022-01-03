package MetaModel;

import java.util.ArrayList;

public abstract class NamedElement {

	abstract public void accept(Visitor v);


	int id;
	java.lang.String nom;
	public NamedElement(java.lang.String nom, int id) {
		this.nom = nom;
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public String getNom() {
		return nom;
	}
	
}

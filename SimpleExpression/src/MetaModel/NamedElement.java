package MetaModel;

public abstract class NamedElement {

	int id;
	String nom;
	public NamedElement(String nom, int id) {
		this.nom = nom;
		this.id = id;
	}
	public abstract void accept(Visitor v);

	
}

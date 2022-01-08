package MetaModel;

public abstract class NamedElement {

	abstract public void accept(Visitor v);


	int id;
	String nom;
	public NamedElement(String nom, int id) {
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

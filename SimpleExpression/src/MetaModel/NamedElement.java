package MetaModel;

public abstract class NamedElement {


	int id;
	java.lang.String nom;
	public NamedElement(java.lang.String nom, int id) {
		this.nom = nom;
		this.id = id;
	}
	public abstract void accept(Visitor v);

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public java.lang.String getNom() {
		return nom;
	}
	public void setNom(java.lang.String nom) {
		this.nom = nom;
	}
	
}

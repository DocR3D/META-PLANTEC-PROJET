package MetaModel;

public abstract class NamedElement {

	abstract public void accept(Visitor v);


	int id;
	String nom;
	
	
	public NamedElement() {
		
	}
	public NamedElement(String nom, int id) {
		this.nom = nom;
		this.id = id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getId() {
		return id;
	}
	public String getNom() {
		return nom;
	}
	
}

package MetaModel;


public class Attribut extends NamedElement {

	String type; //TODO : type = String ou Type

	public Attribut(java.lang.String nom, int id) { // TODO SUPPRIMER 
		super(nom, id);
	}
	
	public Attribut(String type, java.lang.String nom, int id) { 
		super(nom, id);
		this.type = type;
	}

	@Override
	public void accept(Visitor v) {
		v.visitAttribut(this);
	}

	public String valeur() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	
	

	
}

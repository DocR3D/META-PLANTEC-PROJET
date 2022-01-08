package MetaModel;


public class Attribut extends NamedElement {

	String type; //TODO : type = String ou Type
	
	public Attribut(String type, String nom, int id) { 
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

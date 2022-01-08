package MetaModel;


public class Attribut extends NamedElement {

	NamedElement type; //TODO : type = String ou Type
	
	public Attribut(NamedElement type, String nom, int id) { 
		super(nom, id);
		this.type = type;
	}

	public NamedElement getType() {
		return type;
	}

	@Override
	public void accept(Visitor v) {
		v.visitAttribut(this);
	}

	public NamedElement valeur() {
		return type;
	}
	
	public void setType(NamedElement type) {
		this.type = type;
	}
	
	
	

	
}

package MetaModel;


public class Attribut extends NamedElement {

	Type type;

	public Attribut(Type type, java.lang.String nom, int id) {
		super(nom, id);
		this.type = type;
	}

	@Override
	public void accept(Visitor v) {
		v.visitAttribut(this);
	}

	public Type valeur() {
		return type;
	}
	
	

	
}

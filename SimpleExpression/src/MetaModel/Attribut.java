package MetaModel;


public class Attribut extends NamedElement {

	Type type;

	public Attribut(Type type, String nom, int id) {
		super(nom, id);
		this.type = type;
	}

	@Override
	public void accept(Visitor v) {
		type.accept(v);
	}

	public Type valeur() {
		return type;
	}
	
	

	
}

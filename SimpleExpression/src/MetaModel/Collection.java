package MetaModel;

public class Collection extends Attribut {

	int min;
	int max;
	
	String typeCollectionName;
	
	public Collection(NamedElement type, String nom, int id, int min, int max, String typeCollectionName) {
		super(type, nom, id);
		this.min = min;
		this.max = max;
		this.type = type;
		this.typeCollectionName = typeCollectionName;
	}	
	
	public String getTypeCollectionName() {
		return typeCollectionName;
	}

	public int getMin() {
		return min;
	}
	public int getMax() {
		return max;
	}
	
	@Override
	public void accept(Visitor v) {
		v.visitCollection(this);
	}

	public NamedElement valeur() {
		return type;
	}
	public void setType(NamedElement type) {
		this.type = type;
	}


	
}

package MetaModel;

public class StaticArray extends Collection{

	
	public StaticArray(NamedElement type, String nom, int id, int max) {
		super(type, nom, id, 0, max);
	}
	
	public StaticArray(NamedElement type, String nom, int id,int min, int max) {
		super(type, nom, id, min, max);
	}
	public void setType(NamedElement type) {
		this.type = type;
	}

}

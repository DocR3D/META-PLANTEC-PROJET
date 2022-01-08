package MetaModel;

public class StaticArray extends Collection{

	
	public StaticArray(NamedElement type, String nom, int id, int max) {
		super(type, nom, id, 0, max, "List");
	}
	
	public StaticArray(NamedElement type, String nom, int id,int min, int max) {
		super(type, nom, id, min, max, "List");
	}
	public void setType(NamedElement type) {
		this.type = type;
	}

}

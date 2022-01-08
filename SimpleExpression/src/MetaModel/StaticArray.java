package MetaModel;

public class StaticArray extends Collection{

	
	public StaticArray(String type, String nom, int id, int max) {
		super(type, nom, id, 0, max);
	}
	
	public StaticArray(String type, String nom, int id,int min, int max) {
		super(type, nom, id, min, max);
	}
	public void setType(String type) {
		this.type = type;
	}

}

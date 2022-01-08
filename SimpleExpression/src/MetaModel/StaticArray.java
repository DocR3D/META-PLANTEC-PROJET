package MetaModel;

public class StaticArray extends Collection{

	public StaticArray(String nom, int id, int max) { // TODO : supprimer ce constructeur
		super(nom, id, 0, max);
	}
	
	public StaticArray(String type, String nom, int id, int max) {
		super(nom, id, 0, max);
		this.type = type;
	}
	
	public StaticArray(String nom, int id,int min, int max) { // TODO : supprimer ce constructeur
		super(nom, id, min, max);
	}
	
	public StaticArray(String type, String nom, int id,int min, int max) {
		super(nom, id, min, max);
		this.type = type;
	}
	public void setType(String type) {
		this.type = type;
	}

}

package MetaModel;

import java.util.ArrayList;

public abstract class Collection extends Attribut {

	int min;
	int max;
	
	public Collection(String nom, int id, int min, int max) {  //TODO : SUPPRIMER
		super(nom, id);
		this.min = min;
		this.max = max;
	}	
	
	public Collection(String type, String nom, int id, int min, int max) {
		super(nom, id);
		this.min = min;
		this.max = max;
		this.type = type;
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

	public String valeur() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


	
}

package MetaModel;

import java.util.ArrayList;

public abstract class Collection extends Type {

	Type type;
	ArrayList<Object> valeurs;
	int min;
	int max;
	
	public Collection(Type type, int min, int max) {
		super();
		this.type = type;
		this.min = min;
		this.max = max;
		this.valeurs = new ArrayList<>();
	}
	public abstract void addItem(Object item);

	public abstract ArrayList<?> getValeur();
	
	public int getSize() {
		return valeurs.size();
	}


	
}

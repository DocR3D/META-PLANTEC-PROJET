package MetaModel;

import java.util.ArrayList;

public abstract class Collection extends Type {

	Attribut attribut;
	ArrayList<Type> valeurs;
	int min;
	int max;
	
	public Collection(Attribut attribut, int min, int max) {
		super();
		this.attribut = attribut;
		this.min = min;
		this.max = max;
		this.valeurs = new ArrayList<>();
	}
	public abstract void addItem(Type item);

	public abstract Attribut getValeur();
	
	public int getSize() {
		return valeurs.size();
	}
	
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}


	
}

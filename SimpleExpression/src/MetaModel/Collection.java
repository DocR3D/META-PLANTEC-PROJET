package MetaModel;

import java.util.ArrayList;

public abstract class Collection extends Attribut {

	int min;
	int max;
	
	public Collection(Type type,String nom, int id, int min, int max) {
		super(type, nom, id);
		this.min = min;
		this.max = max;
	}	
	
	public int getMin() {
		return min;
	}
	public int getMax() {
		return max;
	}
	
	@Override
	public void accept(Visitor v) {
		v.visitAttribut(this);
	}

	public Type valeur() {
		return type;
	}


	
}

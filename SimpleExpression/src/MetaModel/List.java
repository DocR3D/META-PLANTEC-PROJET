package MetaModel;

import java.util.ArrayList;

public class List extends Collection {

	public List(Type type, int min, int max) {
		super(type, min, max);
	}

	@Override
	public Type valeur() {
		return this;
	}

	@Override
	public void accept(Visitor v) {
		v.visitList(this);
	}

	@Override
	public void addItem(Object item) {
		if(this.getSize() < max ) this.valeurs.add(item);
		
	}

	@Override
	public ArrayList<?> getValeur() {
		return this.valeurs;
	}

}

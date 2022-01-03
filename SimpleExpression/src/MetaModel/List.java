package MetaModel;

import java.util.ArrayList;

public class List extends Collection {

	public List(Attribut type, int min, int max) {
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
	public void addItem(Type item) {
		if(this.getSize() < max ) this.valeurs.add(item);
		
	}

	@Override
	public Attribut getValeur() {
		return null;
	}

}

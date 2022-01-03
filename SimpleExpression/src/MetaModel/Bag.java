package MetaModel;

import java.util.ArrayList;

public class Bag extends Collection{

	public Bag(Attribut type, int min, int max) {
		super(type, min, max);
	}

	@Override
	public Type valeur() {
		return this;
	}

	@Override
	public void accept(Visitor v) {
		v.visitBag(this);
	}

	@Override
	public void addItem(Type item) {
		if(this.getSize() < max) this.valeurs.add(item);
	}

	@Override
	public Attribut getValeur() {
		return null;
	}

}

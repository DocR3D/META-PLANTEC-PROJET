package MetaModel;

import java.util.ArrayList;

public class Set extends Collection {

	public Set(Attribut type, int min, int max) {
		super(type, min, max);
	}

	@Override
	public Type valeur() {
		return this;
	}

	@Override
	public void accept(Visitor v) {
		v.visitSet(this);
	}

	@Override
	public void addItem(Type item) {
		if(this.getSize() < max && !this.valeurs.contains(item)) this.valeurs.add(item);
		
	}

	@Override
	public Attribut getValeur() {
		return null;
	}

}

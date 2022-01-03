package MetaModel;

import java.util.ArrayList;

public class Array extends Collection {
	
	public Array(Type type, int taille) {
		super(type,0,taille); //TODO : Min = 0 ou min = taille ?
	}

	@Override
	public Type valeur() {
		return this;
	}

	@Override
	public void accept(Visitor v) {
		v.visitArray(this);
	}

	@Override
	public void addItem(Object item) {
		if(this.getSize() < max) this.valeurs.add(item);
	}

	@Override
	public ArrayList<?> getValeur() {
		return this.valeurs;
	}


}

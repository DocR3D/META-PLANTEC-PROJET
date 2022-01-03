package MetaModel;

import java.util.ArrayList;

public abstract class Type extends NamedElement {
	public Type(String nom, int id) {
		super(nom, id);
	}
	abstract public ArrayList<NamedElement> valeur();

}

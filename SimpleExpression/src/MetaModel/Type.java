package MetaModel;

public abstract class Type {
	abstract public Type valeur();
	abstract public void accept(Visitor v);
}

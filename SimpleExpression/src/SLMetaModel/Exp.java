package SLMetaModel;

abstract public class Exp {

	abstract public void accept(Visitor v);
	abstract public Exp value();
	
}

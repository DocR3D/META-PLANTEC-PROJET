package MetaModel;

public class Integer extends Type {

	private int valeur;

	public Integer(int valeur) {
		super();
		this.valeur = valeur;
	}

	public Type valeur() {
		return this;
	}
	
	public int getVal() {
		return this.valeur;
	}

	@Override
	public void accept(Visitor v) {
		v.visitInteger(this);
	}
	
	
}

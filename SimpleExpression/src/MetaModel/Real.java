package MetaModel;

public class Real extends Type {

	private float valeur;
	

	public Real(float valeur) {
		super();
		this.valeur = valeur;
	}

	@Override
	public Type valeur() {
		return this;
	}

	@Override
	public void accept(Visitor v) {
		v.visitReal(this);
	}
	
	public float getVal() {
		return this.valeur;
	}
}

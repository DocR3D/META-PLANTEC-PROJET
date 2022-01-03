package MetaModel;

public class String extends Type {

	private String valeur;
	
	
	public String(String valeur) {
		super();
		this.valeur = valeur;
	}

	@Override
	public Type valeur() {
		return this;
	}

	@Override
	public void accept(Visitor v) {
		v.visitString(this);
	}
	public String getVal() {
		return this.valeur;
	}

}

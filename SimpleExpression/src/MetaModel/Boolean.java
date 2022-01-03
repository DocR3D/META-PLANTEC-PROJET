package MetaModel;

public class Boolean extends Type {

	private boolean valeur;
	

	public Boolean(boolean valeur) {
		super();
		this.valeur = valeur;
	}

	@Override
	public Type valeur() {
		return this;
	}
	
	public boolean getVal() {
		return this.valeur;
	}
	
	@Override
	public void accept(Visitor v) {
		v.visitBoolean(this);
		
	}

}

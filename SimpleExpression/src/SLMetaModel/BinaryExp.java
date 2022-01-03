package SLMetaModel;

public abstract class BinaryExp extends Exp {
	protected Exp opg;
	protected Exp opd;
	
	
	public BinaryExp (Exp opg, Exp opd) {
		this.opg = opg;
		this.opd = opd;
	}
	
	public Exp getOpg() {
		return this.opg;
	}
	
	public Exp getOpd() {
		return this.opd;
	}


}

package SLMetaModel;

public class DivExp extends BinaryExp {

	public DivExp(Exp opg, Exp opd) {
		super(opg, opd);
	}
	public void accept(Visitor v) {
		v.visitDivExp(this);
	}

	public Exp value() {
		return null;
	}

}

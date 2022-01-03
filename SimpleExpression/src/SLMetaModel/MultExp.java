package SLMetaModel;

public class MultExp extends BinaryExp {

	public MultExp(Exp opg, Exp opd) {
		super(opg, opd);
	}
	public void accept(Visitor v) {
		v.visitMultExp(this);
	}

	public Exp value() {
		// TODO Auto-generated method stub
		return null;
	}

}

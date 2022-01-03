package SLMetaModel;

public class MinusExp extends BinaryExp {

	public MinusExp(Exp opg, Exp opd) {
		super(opg, opd);
	}
	public void accept(Visitor v) {
		v.visitMinusExp(this);
	}

	public Exp value() {
		// TODO Auto-generated method stub
		return null;
	}

}

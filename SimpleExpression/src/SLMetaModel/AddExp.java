package SLMetaModel;

public class AddExp extends BinaryExp {
	
	
	public AddExp(Exp opg, Exp opd) {
		super(opg, opd);
	}
	
	public void accept(Visitor v) {
		v.visitAddExp(this);
	}

	public Exp value() {
		/*
		 * e.getOpg().accept(this);
		e.getOpd().accept(this);
		
		IntExp opd = (IntExp) this.stk.pop();
		IntExp opg = (IntExp) this.stk.pop();
		this.stk.push(new IntExp(opg.getVal() + opd.getVal()));
		 */
		IntExp eg = (IntExp)this.getOpg().value();
		IntExp ed = (IntExp)this.getOpd().value();
		return new IntExp(eg.getVal()+ ed.getVal());
		
	}

	
}

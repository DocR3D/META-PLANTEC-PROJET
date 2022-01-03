package backEnd;

import java.util.Stack;

import SLMetaModel.*;

public class Calculator extends Visitor {
	Stack<Exp> stk;

	public Calculator () {
		this.stk = new Stack<Exp>();
	}
	
	@Override
	public void visitIntExp(IntExp e) {
		this.stk.push(e);
	}
	@Override
	public void visitAddExp(AddExp e) {
		e.getOpg().accept(this);
		e.getOpd().accept(this);
		
		IntExp opd = (IntExp) this.stk.pop();
		IntExp opg = (IntExp) this.stk.pop();
		this.stk.push(new IntExp(opg.getVal() + opd.getVal()));
	}
	@Override
	public void visitMinusExp(MinusExp e) {
		e.getOpg().accept(this);
		e.getOpd().accept(this);
		IntExp opd = (IntExp) this.stk.pop();
		IntExp opg = (IntExp) this.stk.pop();
		this.stk.push(new IntExp(opg.getVal()-opd.getVal()));
	}
	@Override
	public void visitMultExp(MultExp e) {
		e.getOpg().accept(this);
		e.getOpd().accept(this);
		IntExp opd = (IntExp) this.stk.pop();
		IntExp opg = (IntExp) this.stk.pop();
		this.stk.push(new IntExp(opg.getVal()*opd.getVal()));
	}
	@Override
	public void visitDivExp(DivExp e) {
		e.getOpg().accept(this);
		e.getOpd().accept(this);
		IntExp opd = (IntExp) this.stk.pop();
		IntExp opg = (IntExp) this.stk.pop();
		this.stk.push(new IntExp(opg.getVal()/opd.getVal()));
	}
	
	public Exp result() {
		return this.stk.peek();
	}
	
}

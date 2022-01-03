package SLMetaModel;

public class IntExp extends UnaryExp {
	protected Integer val;
	
	
	public IntExp (Integer val) {
		this.val = val;
	}
	
	public Integer getVal() {
		return this.val;
	}
	
	public void accept(Visitor v) {
		v.visitIntExp(this);
	}
	
	public Exp value() {
		return this;
	}

}

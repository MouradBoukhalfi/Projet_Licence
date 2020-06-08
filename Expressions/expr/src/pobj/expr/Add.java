package pobj.expr;

public class Add extends BinOp implements Expression{
	
	public Add(Expression left, Expression right) {
		super(left,right);
	}

	@Override
	public String toString() {
		return "( "+getLeft()+" + "+getRight()+" )";
	}

	@Override
	public int eval() {
		int tmp= getLeft().eval()+getRight().eval();
		return tmp;
	}

	@Override
	public <T> T accept(IVisitor<T> T) {
		return T.visit(this);
	}
	
	
	
}

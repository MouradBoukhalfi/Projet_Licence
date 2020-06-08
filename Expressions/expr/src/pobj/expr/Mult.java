package pobj.expr;

public class Mult extends BinOp implements Expression{

	public Mult(Expression left, Expression right) {
		super(left,right);
	}
	
	@Override
	public String toString() {
		return getLeft() + " * " + getRight();
	}

	@Override
	public int eval() {
		// TODO Auto-generated method stub
		int tmp= getLeft().eval()*getRight().eval();
		return tmp;
	}
	@Override
	public <T> T accept(IVisitor<T> T) {
		return T.visit(this);
	}
}

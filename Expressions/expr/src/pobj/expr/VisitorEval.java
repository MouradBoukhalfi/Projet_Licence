package pobj.expr;

public class VisitorEval implements IVisitor<Integer>{

	@Override
	public Integer visit(Constant c) {
		// TODO Auto-generated method stub
		return c.eval();
	}

	@Override
	public Integer visit(Add e) {
		// TODO Auto-generated method stub
		return e.eval();
	}

	@Override
	public Integer visit(Mult e) {
		// TODO Auto-generated method stub
		return e.eval();
	}

	@Override
	public Integer visit(Var v) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}
	

}

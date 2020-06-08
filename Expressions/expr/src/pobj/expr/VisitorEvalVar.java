package pobj.expr;

import java.util.Map;

public class VisitorEvalVar extends VisitorEval implements IVisitor<Integer>{
	private Map<String, Integer> mp;
	
	public VisitorEvalVar(Map<String, Integer> mp) {
		super();
		this.mp = mp;
	}
	
	@Override
	public Integer visit(Var v) {
		System.out.println(mp.get(v.getName()));
		return mp.get(v.getName());
	}
}

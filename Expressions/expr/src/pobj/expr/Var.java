package pobj.expr;

public class Var implements Expression{
	
	private final String name;
	
	public Var(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return ""+name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Var other = (Var) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int eval() {
		throw new UnsupportedOperationException();
	}
	@Override
	public <T> T accept(IVisitor<T> T) {
		return T.visit(this);
	}
	
}

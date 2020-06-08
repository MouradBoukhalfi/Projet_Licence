package pobj.expr;

public class Constant implements Expression{
	
	private int entier;
	
	public Constant(int entier) {
		this.entier=entier;
	}
	public Constant() {
		entier=0;
	}
	
	public int getValue() {
		return entier;
	}
	@Override
	public String toString() {
		return ""+entier;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Constant other = (Constant) obj;
		if (entier != other.entier)
			return false;
		return true;
	}
	@Override
	public int eval() {
		return entier;
	}
	@Override
	public <T> T accept(IVisitor<T> T) {
		return T.visit(this);
	}
}

package pobj.expr;

public interface Expression {
   int eval();
   public <T> T accept(IVisitor<T> T) ;
}

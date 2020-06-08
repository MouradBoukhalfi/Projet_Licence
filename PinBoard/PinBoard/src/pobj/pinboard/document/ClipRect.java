package pobj.pinboard.document;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipRect extends AbstractClip implements Clip {
	
	public ClipRect(double left, double top, double right, double bottom, Color color) {
		setGeometry(left, top, right, bottom);
		setColor(color);
	}	

	
	public Clip copy() {
		ClipRect t = new ClipRect(getLeft(),getTop(),getRight(),getBottom(),getColor());
		return t;
	}
	@Override
	public boolean isSelected(double x, double y) {
		return ((x<=getRight())&&(x>=getLeft())&&(y>=getTop())&&y<=getBottom());
	}
	public void draw(GraphicsContext ctx) {
		ctx.setFill(getColor());
		ctx.fillRect(getLeft(),getTop(),getRight(),getBottom());
	}
}

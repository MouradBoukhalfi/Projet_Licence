package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class ToolSelection implements Tool {
	private double x, y;

	@Override
	public void press(EditorInterface i, MouseEvent e) {
		if (e.isShiftDown()) {
			i.getSelection().toogleSelect(i.getBoard(),e.getX(), e.getY());
		}
		else {
			i.getSelection().select(i.getBoard(),e.getX(), e.getY());
		}
		x = e.getX();
		y = e.getY();

	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		for (Clip c: i.getSelection().getContents()) {
			c.move(e.getX() - x, e.getY()-y);
		}
		x = e.getX();
		y = e.getY();

	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {

	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		i.getSelection().drawFeedback(gc);
	}

	@Override
	public String getName(EditorInterface editor) {
		return "selection tool";
	}

	@Override
	public void setColor(Color color) {
		// TODO Auto-generated method stub
		
	}

}
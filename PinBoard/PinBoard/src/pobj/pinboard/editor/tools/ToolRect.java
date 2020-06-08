package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;

public class ToolRect implements Tool {
	private double left;
	private double top;
	private double right;
	private double bottom;
	private Color color = Color.BLUE;
	
	public ToolRect () {
	}
	
	
	public void press(EditorInterface i, MouseEvent e) {
		this.left = e.getX();
		this.top = e.getY();	

	}
	
	public void drag(EditorInterface i, MouseEvent e) {

		this.right=e.getX();
		this.bottom=e.getY();
	}

	public void release(EditorInterface i, MouseEvent e) {
		System.out.println("Release");
		double temp;
		if(this.left>this.right) {
			temp=this.right;
			this.right=this.left;
			this.left=temp;
		}
		if(this.top>this.bottom) {
			temp=this.top;
			this.top=this.bottom;
			this.bottom=temp;
		}	
		i.getBoard().addClip(new ClipRect(this.left, this.top, this.right-this.left, this.bottom-this.top, this.color));
	}
	
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		gc.setStroke(color);
		gc.strokeRect(this.left, this.top, Math.abs(this.right-this.left), Math.abs(this.top-this.bottom));
		
	}

	public String getName(EditorInterface editor) {
		return "Rectangle";
	}

	@Override
	public void setColor(Color color) {
		this.color=color;
		
	}
	
}

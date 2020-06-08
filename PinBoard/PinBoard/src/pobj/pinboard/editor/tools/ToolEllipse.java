package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.editor.EditorInterface;

public class ToolEllipse implements Tool {
	private double left;
	private double top;
	private double right;
	private double bottom;
	private Color color = Color.RED;
	
	public void press(EditorInterface i, MouseEvent e ) {
		this.left = e.getX();
		this.top = e.getY();
		this.right = this.left;
		this.bottom = this.top;
	}

	public void drag(EditorInterface i, MouseEvent e) {
		this.right=e.getX();
		this.bottom=e.getY();

	}

	public void release(EditorInterface i, MouseEvent e) {
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
		i.getBoard().addClip(new ClipEllipse( ((this.left+this.right)/2) - ((this.right-this.left)/2), ((this.top+this.bottom)/2) - ((this.bottom-this.top)/2),
				(this.right-this.left), (this.bottom-this.top), this.color));
	}

	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		gc.setStroke(this.color);
		gc.strokeOval( ((this.left+this.right)/2) - ((this.right-this.left)/2), ((this.top+this.bottom)/2) - ((this.bottom-this.top)/2),
				(this.right-this.left), (this.bottom-this.top));
		
	}

	public String getName(EditorInterface i) {
		return "Ellipse";
	}

	public void setColor(Color color) {
		this.color = color;
		
	}

}

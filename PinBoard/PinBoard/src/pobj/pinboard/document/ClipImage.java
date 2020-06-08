package pobj.pinboard.document;

import java.io.File;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ClipImage extends AbstractClip {
	private Image image;
	private File filename;
	
	public ClipImage(double left, double top, File filename) {
		this.filename = filename;
		image = new Image("file://" + filename.getAbsolutePath());
		setGeometry(left, top, left + image.getWidth(), top + image.getHeight());
		setColor(Color.BLACK);
	}

	public void draw(GraphicsContext ctx) {
		ctx.drawImage(image, getLeft(), getTop());
		
	}

	public ClipImage copy() {
		return new ClipImage(getLeft(), getTop(), filename);
	}

}
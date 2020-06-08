package pobj.pinboard.editor;

import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import java.util.ArrayList;
public class Selection {

	
	private List<Clip> list;
	public Selection() {
		list = new ArrayList<Clip>();
	}
	
	public void select(Board board, double x, double y){
		list.clear();
		for(Clip c: board.getContents()) {
			if( c.isSelected(x, y)){
				list.add(c);
				System.out.println(board.getContents());
				return;
			}
		}
	}
	public void toogleSelect(Board board, double x, double y){
		for(Clip c: board.getContents()) {
			if( c.isSelected(x, y)){
				if(list.contains(c)==false) {
					list.add(c);
					return;
				}
				if(list.contains(c)==true) {
					list.remove(c);
					return;
				}
			}
			
		}
	}
	public void clear() {
		list.clear();
	}
	public List<Clip> getContents(){
		return list;
	}
	public void drawFeedback(GraphicsContext gc) {
		
	}
	
}
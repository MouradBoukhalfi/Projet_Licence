package pobj.pinboard.editor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pobj.pinboard.document.Board;
import pobj.pinboard.editor.tools.ToolEllipse;
import pobj.pinboard.editor.tools.ToolRect;
import pobj.pinboard.editor.tools.ToolSelection;
import pobj.pinboard.editor.tools.Tool;


public class EditorWindow implements EditorInterface, ClipboardListener {
	
	private MenuBar menus;
	private ToolBar bouttons;
	private Canvas canvas;
	private Label label = new Label();
	private VBox vBox;
	private Board board;
	private Stage stage;
	private Tool outil;
	private Color color = Color.BLUE;
	private Selection selection = new Selection();
	private MenuItem paste;
	
	public EditorWindow(Stage stage) {
		EditorWindow ew = this;
		Clipboard.getInstance().addListener(this);
		this.stage = stage;
		this.stage.setTitle("PinBoard");
		this.board=new Board();
		this.vBox = new VBox();
		this.newCanvas(400 ,600 );
		this.newBarreMenu();
		this.newToolBar();
		this.outil = new ToolRect();
		this.setStatut();
		
		this.vBox.getChildren().addAll(this.menus , new Separator() ,this.bouttons,new Separator(),this.canvas,new Separator(),this.label);
		Scene scene = new Scene(this.vBox);
		
		this.stage.setScene(scene);
		this.draw();
		this.stage.show();
		
		this.canvas.setOnMousePressed( new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e){
				ew.buttonPressed( e );
			}
		}
		);
		this.canvas.setOnMouseDragged( new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e){
				ew.buttonDragged( e );
			}
		}
		);
		this.canvas.setOnMouseReleased( new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e){
				ew.buttonReleased( e );
			}
		}
		);
	}

	protected void buttonPressed(MouseEvent e) {
		this.outil.press(this, e );
		this.outil.setColor(this.color);
		
	}


	protected void buttonDragged(MouseEvent e) {
		this.outil.drag( this , e );
		this.draw();
		this.outil.drawFeedback(this, this.canvas.getGraphicsContext2D());
	}


	protected void buttonReleased(MouseEvent e) {
		this.outil.release( this , e );
		this.draw();
	}


	private void setStatut() {
		this.label.textProperty().set("Outil utilisé  : " + this.outil.getName(this));
		
	}

	private void newToolBar() {
		
		EditorWindow ew= this;
		Button rect = new Button( "Rectangle");
		Button ell = new Button( "Ellipse");
		Button selec = new Button("Select");
		rect.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				ew.outil = new ToolRect();
				ew.setStatut();
			}
		});
		
		ell.setOnAction( new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				ew.outil = new ToolEllipse();
				ew.setStatut();
			}
		});
		
		selec.setOnAction( new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				ew.outil = new ToolSelection();
				ew.setStatut();
			}
		});
		this.bouttons = new ToolBar( rect, ell, selec);
	}

	private void newBarreMenu() {
		Menu menuFile = this.newMenuFile() ;
		Menu menuCouleur = this.newMenuColor();
		Menu menuEdit = this.newMenuEdit();
		this.menus  = new MenuBar(menuFile ,menuEdit, menuCouleur);
	}

	private Menu newMenuColor() {
		EditorWindow ew = this;
		Menu menu = new Menu("Couleur");
		MenuItem bleu = new MenuItem("Bleu");
		MenuItem vert = new MenuItem("Vert");
		MenuItem rouge = new MenuItem( "Rouge");
		bleu.setOnAction( 
				new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				ew.setColor( Color.BLUE);
				ew.setStatut();
			}
		});
		vert.setOnAction( 
				new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				ew.setColor( Color.GREEN);
				ew.setStatut();
			}
		});
		rouge.setOnAction( 
				new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				ew.setColor( Color.RED );
				ew.setStatut();
			}
		});
		menu.getItems().addAll(  bleu, vert , rouge );
		return menu;
	}



	private Menu newMenuFile() {
		Stage stage = this.stage;
		Menu menu = new Menu("File");
		MenuItem newI = new MenuItem("New");
		MenuItem close = new MenuItem( "Close");
		
		newI.setOnAction( 
				new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				new EditorWindow(new Stage());
			}
		});
		
		close.setOnAction( 
				new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				stage.close();
			}
		});
		menu.getItems().addAll(  newI, close );
		
		return menu;
	}
	private Menu newMenuEdit() {
		Menu menu = new Menu("Edit");
		paste = new MenuItem("Paste"); 
		MenuItem copy = new MenuItem("Copy");
		MenuItem delete = new MenuItem("delete");
		
		paste.setOnAction( (e)-> {board.addClip((Clipboard.getInstance().copyFromClipboard()));draw();});
		if (Clipboard.getInstance().isEmpty()) {
			paste.setDisable(true);
		}
		else {
			paste.setDisable(false);

		}

		delete.setOnAction( (e)-> {board.removeClip(selection.getContents());selection.clear();draw();});
		menu.getItems().addAll(copy, paste, delete);
		return menu;
	}

	private void newCanvas(int hauteur, int longueur) {
		this.canvas = new Canvas( longueur , hauteur );
		
	}

	public Board getBoard() {
		return this.board;
	}
	
	private void draw(){
		this.board.draw(this.canvas.getGraphicsContext2D());
	}

	public Selection getSelection() {
		return selection;
	}

	public CommandStack getUndoStack() {
		return null;
	}

	@Override
	public void clipboardChanged() {
		if (Clipboard.getInstance().isEmpty()) {
			paste.setDisable(true);
		}
		else {
			paste.setDisable(false);
		}
	}
	protected void setColor(Color red) {
		this.color = red;
		
	}

}
package application.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/*
 * Controllerklasse som styrer kommunikasjon med bruker og GUI
 */

public class MainController2 implements Initializable {

    @FXML private ChoiceBox typeSelector;
    @FXML private AnchorPane propertyView;

	public void addAsset(ActionEvent event) {

	}

    // denne metoden blir kalt automatisk i oppstarten av programmet
    // kan assosieres med konstrukt�r
    public void initialize(URL location, java.util.ResourceBundle resources) {ChoiceBox cb = new ChoiceBox();
        typeSelector.setItems(FXCollections.observableArrayList("game.character.Player", "game.NPC ", "Object"));
        typeSelector.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, number2) -> typeChange(typeSelector.getItems().get((Integer) number2).toString()));
    }

    private void typeChange(String type) {
        try {
            Pane pane = FXMLLoader.load(getClass().getResource("/application/layout/" + type + ".fxml"));
            propertyView.getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Intern modell-klasse (deklarert her for enkelhetens skyld)
	// Burde v�re deklarert som en modell (f.eks. i model pakke ved bruk
	// av MVC
	private static class Point {
		public double x,y;
		public void draw(GraphicsContext gc, Color drawColor, double size) {
			gc.setFill(drawColor);
			gc.fillArc(x, y, size, size, 0, 360, ArcType.ROUND);
		}
	}
	
	// interne objekter relatert til GUI
	@FXML private MenuBar menuBar;
	@FXML private Canvas graphics;
	@FXML private ColorPicker colorPicker;
	@FXML private Slider sizeSlider;
	private List<Point> plist;

	
	// hjelpemetode som tegner grafikk til 'canvas' omr�det i GUI
	private void draw() {
		GraphicsContext gc = graphics.getGraphicsContext2D();
		gc.clearRect(0, 0, graphics.widthProperty().doubleValue(), graphics.heightProperty().doubleValue());
		for ( Point p : plist ) {
			p.draw(gc, colorPicker.getValue(), sizeSlider.getValue());
		}
	}
	
	/*
	 * F�lgende metoder h�ndterer actions fra bruker (via GUI)
	 */
	
	public void mouseDragged(MouseEvent event) {
		Point p = new Point();
		p.x = event.getX();
		p.y = event.getY();
		plist.add(p);
		draw();
	}
	
	public void exitEvent(ActionEvent event) {
		System.exit(0);
	}
	
	public void clearEvent(ActionEvent event) {
		plist.clear();
		draw();
	}

	
	public void newColorEvent(ActionEvent event) {
		draw();
	}
	
	public void newSizeEvent(MouseEvent e) {
		draw();
	}
}

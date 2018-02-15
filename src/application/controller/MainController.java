package application.controller;

import FileHandler.Asset;
import FileHandler.FileHandler;
import game.Map;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

public class MainController implements Initializable {

	private static final int NUMBER_OF_BITS = 16;

	@FXML
	Canvas graphics;

	@FXML
	ListView assets;

	private Map map;

	public void draw() {
		GraphicsContext gc = graphics.getGraphicsContext2D();
		Asset[][] board = map.getBoard();

		// TODO: Lage flere farger, bakgrunn
		if(graphics != null && board != null) {
			graphics.setHeight(map.getHeight() * NUMBER_OF_BITS);
			graphics.setWidth(map.getWidth() * NUMBER_OF_BITS);

			gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getWidth());
			for(int y = 0; y < board.length; y++) {
				for (int x = 0; x < board[0].length; x++) {
					if(board[y][x] == null) {
						gc.setFill(Color.RED);
						gc.fillRect(x * NUMBER_OF_BITS, y * NUMBER_OF_BITS, NUMBER_OF_BITS, NUMBER_OF_BITS);
					}
				}
			}
		}
	}

    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
		this.map = new Map();

		FileHandler fh = new FileHandler();

		ListView<String> list = new ListView<String>();
		ObservableList<String> items = fh.getAllAssets();
		assets.setItems(items);


		draw();
    }
}

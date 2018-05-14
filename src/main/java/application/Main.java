package main.java.application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

/**
 * This class creates a JavaFX GUI program.
 * We use FXML loader to download it in FXML that describes GUI.
 * @author
 */
public class Main extends Application {

	/**
	 * Implements the visible objects in the game.
	 * @param primaryStage is the frame of the gameboard.
	 * @throws Exception telling the compiler that a exception may be thrown in this method.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../resources/view/Main.fxml"));

		Scene scene = new Scene(root, 800, 800); //Sets the size of the board.

		primaryStage.setResizable(false);
		primaryStage.setTitle("HAC"); //Sets the title "main.java.HAC" of the game in a scene.
		primaryStage.setScene(scene); //Sets a scene of the game.
		primaryStage.show(); //Shows the scene.
	}

	/**
	 * Makes it possible to launch it to terminal.
	 * @param args makes it possible to run our program with a statement, then args will contain the statement.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
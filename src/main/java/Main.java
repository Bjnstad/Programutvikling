package main.java;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

/**
 * In this class we create a JavaFX GUI program.
 * We use FXML loader to download it in FXML that describes GUI.
 * @author ceciliethoresen
 */
public class Main extends Application {

	/**
	 * This method implements the visible objects in the game.
	 * @param primaryStage is the frame of the gameboard.
	 * @throws Exception telling the compiler that a exception may be thrown in this method.
	 * @author
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../resources/view/Main.fxml"));

		Scene scene = new Scene(root, 400, 800); //Sets the size of the board.

		primaryStage.setResizable(false);
		primaryStage.setTitle("main/java/HAC"); //Sets the title "main.java.HAC" of the game in a scene.
		primaryStage.setScene(scene); //Sets a scene of the game.
		primaryStage.show(); //Shows the scene.
	}

	/**
	 * This method makes it possible to launch it to terminal.
	 * @param args makes it possible to run our program with a statement, then args will contain the statement.
	 * @author
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
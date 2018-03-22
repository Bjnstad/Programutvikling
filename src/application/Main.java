package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

/*
 * Opprett JavaFX GUI program. Bruk FXMLLoader for � laste inn FXML
 * som beskriver GUI.
 */
public class Main extends Application {

	/**
	 *
	 * @param primaryStage
	 * @throws Exception
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("layout/Main.fxml"));

		Scene scene = new Scene(root, 800, 800);

		primaryStage.setResizable(false);
		primaryStage.setTitle("HAC");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * ...
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
package application.controller.subController;

import application.controller.mainController.Controller;
import application.controller.mainController.GameController;
import application.model.ScoreBoard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;


/**
 * This class simulates rolling a die of controller.
 * @author
 */
public class DieController implements SubController {

    GameController gameController;

    @FXML
    ListView HighScore;




    /**
     * Sets the subController.
     * @param controller can´ be null.
     */
    @Override
    public void setSubController(Controller controller) {
        if (controller == null) throw new NullPointerException("Controller cant be null");
        if (!(controller instanceof GameController)) throw new IllegalStateException("Unexpected class, should be GameController");
        this.gameController = (GameController) controller; // Cast to GameController.

    }

    /**
     * Initialization done in constructor.
     */
    @Override
    public void init() {
        ScoreBoard scoreBoard = new ScoreBoard(gameController.getWorld().getCurrentLevel(), gameController.getWorld().getGameMap().getMapFileName());
        scoreBoard.handlePlayerName();
        HighScore.setItems(scoreBoard.getScoreList());
    }

    /**
     * This method terminates the currently running program.
     * @param event allows us to access the properties of the ActionEvent.
     */
    @FXML
    public void exit(ActionEvent event) {
        gameController.exit();
    }
}

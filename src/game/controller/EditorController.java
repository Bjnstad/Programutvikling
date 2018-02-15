package game.controller;

import game.GameState;
import game.State;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;




public class EditorController extends GameState implements Controller {
    private MainController mainController;


    public EditorController() {
        super(State.EDITOR);
    }



        @Override
        public void setMainController (MainController mainController) {
            this.mainController = mainController;
        }

            @Override
            public void initiate () {

            }

            @Override
            public void render () {

            }

            @Override
            public void onClose () {

            }


}

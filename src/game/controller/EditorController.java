package game.controller;

import FileHandler.FileHandler;
import game.GameState;
import game.State;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;




public class EditorController extends GameState implements Controller {

    private MainController mainController;
    private FileHandler fileHandler;


    @FXML
    ListView listView;


    public EditorController() {
        super(State.EDITOR);
    }

    @FXML
    public void newFile(ActionEvent event){
        System.out.println("");


    }

    @Override
    public void setMainController (MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initiate () {
        FileHandler fileHandler = new FileHandler();


        //ObservableList<String> items = fileHandler.getAllAssets();

        listView.setItems(fileHandler.getAllNames());
        listView.setCellFactory(param -> fileHandler.getAllAssets());

    }


    @Override
    public void render () {


    }

    @Override
    public void onClose () {

    }


    @Override
    public EventHandler<KeyEvent> getEventHandler() {
        return null;
    }


}

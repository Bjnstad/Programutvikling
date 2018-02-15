package game.controller;

import game.GameState;
import game.State;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;




public class EditorController extends GameState implements Controller {
    private MainController mainController;


    @FXML
    ListView listView;

    public EditorController() {
        super(State.EDITOR);
    }



    @Override
    public void setMainController (MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initiate () {
        ObservableList<String> items = getAllAssets();
        listView.setItems(items);

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

    public ObservableList<String> getAllAssets() {
        File folder = new File("assets");
        if(folder == null) return null;

        ObservableList<String>  list = FXCollections.observableArrayList ();

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                // TODO: Maybe handle dir
            } else {
                list.add(fileEntry.getName());
                System.out.println(fileEntry.getName());
            }
        }

        return list;

    }


}

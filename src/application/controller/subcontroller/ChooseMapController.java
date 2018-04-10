package application.controller.subcontroller;

import application.controller.Controller;
import application.controller.GameController;
import application.controller.MainMenuController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.awt.event.ActionEvent;
import java.io.File;

public class ChooseMapController implements SubController {

    static final File dir = new File("assets/maps");
    private MainMenuController mainMenuController;

    public ChooseMapController() {

        maps.setItems(getAllNames());
    }


    private ObservableList<String> getAllNames() {
        if (!dir.isDirectory()) return null;
        ObservableList<String> res = FXCollections.observableArrayList();

            for (final File f : dir.listFiles(IMAGE_FILTER)) {
            System.out.println(getFileExtension(f));

            if(getFileExtension(f).equals("png")) {
                System.out.println(f.getName());
                res.add(f.getName());
            }
        }

        return res;
    }

    private String getFileExtension(File file) {
        String name = file.getName();
        try {
            return name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }


    @FXML
    ListView maps;

    @FXML
    public void importMap(ActionEvent event) {

    }

    @FXML
    public void play(ActionEvent event) {

    }

    @Override
    public void setSubController(Controller controller) {
        if (controller == null) throw new NullPointerException("Controller cant be null");
        if (!(controller instanceof MainMenuController))
            throw new IllegalStateException("Unexpected class, should be MainMenuController");
        this.mainMenuController = (MainMenuController) controller; // Cast to MainMenuController.
    }
}

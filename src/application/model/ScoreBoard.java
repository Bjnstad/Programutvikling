package application.model;


import hac.model.filehandler.FileHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;

/**
 * Created by henrytran1 on 16/05/2018.
 */
public class ScoreBoard {

    private int currentLevel;
    private String mapName;
    private String scoreFile = "assets/score/";


    ObservableList<String> scoreList = FXCollections.observableArrayList();

    public ScoreBoard(int currentLevel, String mapName) {
        this.currentLevel = currentLevel;
        String[] mapNameNoExt = mapName.split("\\.");

        this.mapName = mapNameNoExt[0] + ".txt";
    }




    public void handlePlayerName(){
        final Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        BorderPane root = new BorderPane();

        primaryStage.setTitle("Enter player name");
        primaryStage.setScene(new Scene(root));

        Button submit = new Button("Submit");

        Label name = new Label("Name: ");
        TextField inputName = new TextField ();


        VBox Vertikalboks = new VBox(name, inputName);

        root.setLeft(Vertikalboks);
        root.setBottom(submit);
        primaryStage.show();

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                saveScore(inputName.getText());
                parseScore();


                primaryStage.close();

            }
        });
    }

    private void saveScore(String playerName){

        String scoreToSave = ","+playerName + " Reached level " + currentLevel;
        try(FileWriter fw = new FileWriter(scoreFile+mapName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.print(scoreToSave);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseScore(){
        try {

            BufferedReader b = new BufferedReader(new FileReader(scoreFile+mapName));
            String score = b.readLine().toString();

            String[] scoreSeperate = score.split(",");
            for (int i = 0; i <scoreSeperate.length; i++) {
                if(scoreSeperate[i].equals(""))continue;
                scoreList.add(scoreSeperate[i]);

            }

        }catch (FileNotFoundException e) {
            e.printStackTrace();
            FileHandler.showAlert("Corrupt File!", "File is corrupt.");
        }catch (IOException e) {
            e.printStackTrace();
            FileHandler.showAlert("Corrupt File!", "File is corrupt.");
        }catch (ArrayIndexOutOfBoundsException e){
            FileHandler.showAlert("Corrupt File!", "File is corrupt.");
        }
    }




    public ObservableList<String> getScoreList() {
        return scoreList;
    }
}

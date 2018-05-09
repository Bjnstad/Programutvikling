package main.java.model.filehandler;


import main.java.controller.GameController;

/**
 * Saves and loads game files(.gHac), all files will be collected and saved at /assets/savegame
 */

public class GameHandler {

    public boolean saveGame(GameController game, String name) {
        if(fileExists(name)) return false;

        String result = "";

        //result += game.getGameMap().toString();

        return true;
    }

    private boolean fileExists(String name) {
        return false;
    }


}

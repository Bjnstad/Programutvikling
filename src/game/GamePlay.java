package game;

public class GamePlay {

    private GameState state;


    private void setState(GameState state) {
        this.state.onClose(); // Close up old state.
        this.state = state;
        this.state.initiate(); // Initiate
    }

    public State getState() {
        return this.state.getState();
    }

}

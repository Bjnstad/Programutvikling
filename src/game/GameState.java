package game;

/**
 * Created by henrytran1 on 06/02/2018.
 */
public abstract class GameState {
    private State state;

    public GameState(State state){
        this.state = state;
    }
    public abstract void initiate();
    public abstract void render();
    public abstract void onClose();

    public  State getState() {
        return this.state;
    }
}

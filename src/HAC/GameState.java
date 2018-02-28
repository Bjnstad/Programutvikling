package HAC;

import application.State;

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

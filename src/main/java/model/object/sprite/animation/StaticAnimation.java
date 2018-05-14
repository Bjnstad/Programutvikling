package main.java.model.object.sprite.animation;

public class StaticAnimation implements Animation {

    private int x;//
    private int y;//

    public StaticAnimation (int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public void increaseInterval() {
        // No interval needed
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}

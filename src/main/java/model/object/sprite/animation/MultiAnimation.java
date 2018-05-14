package main.java.model.object.sprite.animation;


import main.java.model.object.sprite.Direction;

public class MultiAnimation implements Animation {

    private Direction direction;//
    private int interval = 0;
    private int frames;//

    public MultiAnimation(int frames) {
        this.frames = frames;
        this.direction = Direction.DOWN;
    }


    public void setDirection(Direction direction) {
        increaseInterval();
        this.direction = direction;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public int getFrames(){
        return frames;
    }



    @Override
    public void increaseInterval() {
        interval++;
        if(interval >= frames) interval = 0;
    }

    @Override
    public int getX() {
        return interval;
    }

    @Override
    public int getY() {
        switch (direction) {
            case UP:
                return 0;
            case LEFT:
                return 1;
            case DOWN:
                return 2;
            case RIGHT:
                return 3;
            default:
                return 0;
        }
    }
}

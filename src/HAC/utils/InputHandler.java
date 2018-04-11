package HAC.utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class contains inputhandler that implements keylistener.
 * What happens when a key is either typed, pressed or released.
 * @author ceciliethoresen
 */
public class InputHandler implements KeyListener {

    /**
     * In this method when a key is typed
     * @param e
     * @author ceciliethoresen
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * When we press a key the method gets a key code from e?
     * @param e
     * @author ceciliethoresen
     */
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
    }

    /**
     * In this method when a key is releasedm then ......
     * @param e
     * @author ceciliethoresen
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
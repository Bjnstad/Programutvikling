package main.java.HAC.utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class contains inputHandler that implements KeyListener.
 * What happens when a key is either typed, pressed or released.
 * @author
 */
public class InputHandler implements KeyListener {

    /**
     * When a key is typed.
     * @param e allows you to access the properties of KeyEvent.
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * When a key is pressed.
     * @param e allows you to access the properties of KeyEvent.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
    }

    /**
     * When a key is released.
     * @param e allows you to access the properties of KeyEvent.
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
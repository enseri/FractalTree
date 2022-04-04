package src;

import java.awt.event.*;

public class Keyboard implements KeyListener {
    int key;
    int x;
    int types;

    public int getKey() {
        return key;
    }

    public int getTypes() {
        return types;
    }

    public void keyPressed(KeyEvent e) {
        x++;
        if(x == 3) {
            key = e.getKeyCode();
            types++;
        }
    }

    public void keyReleased(KeyEvent e) {
        x = 0;
        System.out.println(e.getKeyCode());
        key = e.getKeyCode();
        types++;
        e.consume();
    }

    public void keyTyped(KeyEvent e) {

    }

}
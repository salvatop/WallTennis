package draw;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Pad {
    
    private int padXPos = 0;
    private int padXSpeed = 0;

    private final WallTennis GAME;
    private final int PAD_Y_POS = 520;
    private final int PAD_WIDTH = 80;
    private final int PAD_HEIGHT = 13;

    private static int padSpeed = 2;


    public Pad(WallTennis game) {
        this.GAME = game;
    }

    public void updatePadPosition() {
        if (padXPos + padXSpeed > 0 && padXPos + padXSpeed < GAME.getWidth() - PAD_WIDTH) {
            padXPos = padXPos + padXSpeed;
        }
    }

    public void paint(Graphics2D graphics2D) {
        graphics2D.fillRect(padXPos, PAD_Y_POS, PAD_WIDTH, PAD_HEIGHT);
        graphics2D.setColor(Color.WHITE);
    }

    public void keyReleased(KeyEvent e) { padXSpeed = 0; }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            padXSpeed = - padSpeed;

        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            padXSpeed = padSpeed;
    }

    public Rectangle getPadBoundaries() {
        return new Rectangle(padXPos, PAD_Y_POS, PAD_WIDTH, PAD_HEIGHT );
    }

    public int getPadYPos() {
        return PAD_Y_POS;
    }

    public static void setPadSpeed(int speed) {
        padSpeed = speed;
    }

    public static int getPadSpeed() {
        return padSpeed ;
    }
}
package draw;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Pad {
    
    private int padXPos = 0;
    private int padXSpeed = 0;
    private WallTennis game;

    private final static int padTopY = 520;
    final static int PAD_WIDTH = 60;
    final static int PAD_HEIGHT = 10;

    private final int PAD_SPEED = 2;

    public Pad(WallTennis game) {
        this.game = game;
    }

    public void updatePadPosition() {
        if (padXPos + padXSpeed > 0 && padXPos + padXSpeed < game.getWidth() - PAD_WIDTH)
            padXPos = padXPos + padXSpeed;
    }

    public void paint(Graphics2D g) {
        g.fillRect(padXPos, padTopY, PAD_WIDTH, PAD_HEIGHT);
    }

    public void keyReleased(KeyEvent e) {
        padXSpeed = 0;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            padXSpeed = - PAD_SPEED;

        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            padXSpeed = PAD_SPEED;
    }

    public Rectangle getBounds() {
        return new Rectangle(padXPos, padTopY, PAD_WIDTH, PAD_HEIGHT * 2);
    }

    public int getTopY() {
        return padTopY;
    }
}
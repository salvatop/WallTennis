package draw;

import javax.swing.*;
import java.awt.*;

import static java.awt.image.ImageObserver.ABORT;


public class Ball {
    private WallTennis game;

    private int ballXPos = 0;
    private int ballYPos = 0;
    private int speedX = 2;
    private int speedY = 2;

    private final int BALL_VELOCITY = 2;
    private final int BALL_SIZE = 30;

    public Ball(WallTennis game) {
        this.game = game;
    }

    public void updateBallPosition() {

        if (collision()) {
            ballYPos = -1;
            speedY = game.pad.getTopY() - BALL_SIZE;
        }
        if (ballXPos + speedX < 0) speedX = BALL_VELOCITY; // left border
        if (ballXPos + speedX > game.getWidth() - BALL_SIZE) speedX = - BALL_VELOCITY; //right border
        if (ballYPos + speedY < 0) speedY = BALL_VELOCITY; // top
        if (ballYPos + speedY > game.getHeight() - BALL_SIZE ) {
            speedY = - BALL_VELOCITY;
            //ballXPos = 0;
            //ballYPos = 0;
            //gameOver(); //bottom(GAME OVER)
        }
        //update position
        ballXPos = ballXPos + speedX;
        ballYPos = ballYPos + speedY;
    }
    public void gameOver() {
        JOptionPane.showMessageDialog(game, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }

    public void paint(Graphics2D g) {
        g.fillOval(ballXPos, ballYPos, BALL_SIZE, BALL_SIZE);
    }

    private boolean collision() {
        return game.pad.getBounds().intersects(getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(ballXPos, ballYPos, BALL_SIZE, BALL_SIZE);
    }

}
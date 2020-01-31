/*


 */

package draw;

import javax.swing.*;
import java.awt.*;

public class Ball {
    private WallTennis game;

    private int ballXPos = 0;
    private int ballYPos = 0;
    private int speedX = 1;
    private int speedY = 1;
    private int hits = 0;
    private int nbOfIncreasedPadSpeed = 0;
    private int ballVelocity = 1;
    private int record = 0;

    private final int BALL_SIZE = 17;


    public Ball(WallTennis game) {
        this.game = game;
    }

    public void updateBallPosition() {
        if(thereIsPadCollision()) {
            record++;
            if(hits >= 2) {
                ballVelocity++;
                nbOfIncreasedPadSpeed++;
                if(nbOfIncreasedPadSpeed % 2 != 0) {
                    Pad.setPadSpeed(Pad.getPadSpeed() + 1);
                    System.out.println("Increased Pad Speed to: " + nbOfIncreasedPadSpeed);
                }
                hits = 0;
                System.out.println("Increased Ball Speed to: " + ballVelocity);

            } else {
                ballYPos = game.pad.getPadYPos() - BALL_SIZE;
                speedY = -ballVelocity;
                hits++;
                System.out.println("Hit " + "(" + hits + ")");
            }
        }

        else if (ballXPos + speedX < 0) speedX = ballVelocity;
        else if (ballYPos + speedY < 0) speedY = ballVelocity;

        else if (ballXPos + speedX > game.getWidth() - BALL_SIZE) speedX = -ballVelocity; //horizontal boundaries

        else if (ballYPos + speedY > game.getHeight() - BALL_SIZE ) { speedY = - ballVelocity; score();} //vertical boundaries

        //update position
        ballXPos = ballXPos + speedX;
        ballYPos = ballYPos + speedY;
    }

    public void score() {
        System.out.println("Game Over\n");
        JOptionPane.showMessageDialog(null,"Your record is " + record
                + " hits!", "Game Over", JOptionPane.PLAIN_MESSAGE);
        hits = 0;
        ballXPos = 0;
        ballYPos = 0;
        ballVelocity = 1;
        Pad.setPadSpeed(2);
    }

    //draw the ball
    public void paint(Graphics2D graphics2D) {
        graphics2D.fillOval(ballXPos, ballYPos, BALL_SIZE, BALL_SIZE);
        graphics2D.setColor(Color.WHITE);
    }

    public boolean thereIsPadCollision() {
        return game.pad.getPadBoundaries().intersects(getBallBoundaries());
    }

    public Rectangle getBallBoundaries() {
        return new Rectangle(ballXPos, ballYPos, BALL_SIZE, BALL_SIZE);
    }
}
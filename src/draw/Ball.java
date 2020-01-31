/*


 */

package draw;

import java.awt.*;


public class Ball {
    private WallTennis game;

    private int ballXPos = 0;
    private int ballYPos = 0;
    private int speedX = 1;
    private int speedY = 1;
    private int hits = 0;
    private int nbIncreasedPadSpeed = 0;
    private int ballVelocity = 1;

    private final int BALL_SIZE = 20;
    

    public Ball(WallTennis game) {
        this.game = game;
    }

    public void updateBallPosition() {

        if(padCollision()) {
            if(hits >= 3) {
                ballVelocity++;
                nbIncreasedPadSpeed++;
                if(nbIncreasedPadSpeed % 2 != 0) {
                    Pad.setPadSpeed(Pad.getPadSpeed() + 1);
                    System.out.println("nbIncreasedPadSpeed++ " + nbIncreasedPadSpeed);
                }
                hits = 0;
                System.out.println("speed++");

            } else {
                ballYPos = game.pad.getPadYPos() - BALL_SIZE;
                speedY = -ballVelocity;
                hits++;
                System.out.println("hit++ " + hits);
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
        System.out.println("goal");
        hits = 0;
        ballVelocity = 1;
        
        Pad.setPadSpeed(1);
    }

    //draw the ball
    public void paint(Graphics2D graphics2D) {
        graphics2D.fillOval(ballXPos, ballYPos, BALL_SIZE, BALL_SIZE);
        graphics2D.setColor(Color.WHITE);
    }

    public boolean padCollision() {
        return game.pad.getBounds().intersects(this.getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(ballXPos, ballYPos, BALL_SIZE, BALL_SIZE);
    }
}
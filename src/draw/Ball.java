package draw;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;

public class Ball {
    private final WallTennis GAME;
    private final int BALL_SIZE = 17;

    private Score score;
    private int ballXPos = 0;
    private int ballYPos = 0;
    private int speedX = 1;
    private int speedY = 1;
    private int hits = 0;
    private int nbOfIncreasedPadSpeed = 0;
    private int ballVelocity = 1;
    private int record = 0;


    public Ball(WallTennis game) {
        this.GAME = game;
        score = new Score(GAME);
    }

    public void updateBallPosition() {
        if(thereWasAPadCollision()) {
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
                ballYPos = GAME.pad.getPadYPos() - BALL_SIZE;
                speedY = -ballVelocity;
                hits++;
                System.out.println("Hit " + "(" + hits + ")");
            }
        }

        else if (ballXPos + speedX < 0) {
            speedX = ballVelocity;
        }

        else if (ballYPos + speedY < 0) {
            speedY = ballVelocity;
        }

        else if (ballXPos + speedX > GAME.getWidth() - BALL_SIZE) { //horizontal boundaries
            speedX = -ballVelocity;
        }

        else if (ballYPos + speedY > GAME.getHeight() - BALL_SIZE ) { //vertical boundaries
            speedY = - ballVelocity;
            score.gameOver();
        }

        //update position
        ballXPos = ballXPos + speedX;
        ballYPos = ballYPos + speedY;
    }

    //draw the ball
    public void paint(Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillOval(ballXPos, ballYPos, BALL_SIZE, BALL_SIZE);
    }

    public boolean thereWasAPadCollision() {

        return GAME.pad.getPadBoundaries().intersects(getBallBoundaries());
    }

    public Rectangle getBallBoundaries() {

        return new Rectangle(ballXPos, ballYPos, BALL_SIZE, BALL_SIZE);
    }

    public void setBallXPos(int ballXPos) {
        this.ballXPos = ballXPos;
    }

    public void setBallYPos(int ballYPos) {
        this.ballYPos = ballYPos;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public void setBallVelocity(int ballVelocity) {
        this.ballVelocity = ballVelocity;
    }

    public int getRecord() {
        return this.record;
    }
}
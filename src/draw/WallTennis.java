/*
########## ########## ########## ########## W A L L   T E N N I S ########## ########## ########## ##########

* @author  Salvatore Palazzo
* @version 1.0
* @since   2020-02-01

* A simple tennis survival game that use JFrame to build a UI and KeyListener/KeyEvent from the class JPanel
* to bind the keyboard keys to pad moves.

* Every two round the ball speed increase and so pad does as well but with a slightly different ratio.

* The goal of the game it's survive as longer as you can to score the highest record.

########## ########## ########## ########## CLASSES ########## ########## ########## ##########

* @WallTennis this Class, instantiate the listener for the keyboard binds, create the frame
    to display the UI and override the method paint to draw the ball and pad.


* @Pad Class contains the info to draw a pad and the keyboard binds code

* @Ball Class contains the info to draw a ball and the game and collision logic to determine whatever
    the ball hit the pad how to increase the ball and the pad speed during the game or if hit the
    the edges of the board to bounce in the opposite direction.

* @Score Class contains the logic to display the score at game over.

########## ########## ########## ########## ########## ########## ########## ########## ########## ##########
 */

package draw;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class WallTennis extends JPanel {

    private final static int WIDTH = 400;
    private final static int HEIGHT = 600;
    private final static WallTennis GAME = new WallTennis();

    Ball ball = new Ball(this);
    Pad pad = new Pad(this);

    //only needed to assign key listener to pad class
   private WallTennis() {
            KeyListener listener = new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) { }

                @Override
                public void keyPressed(KeyEvent e) { pad.keyPressed(e);}

                @Override
                public void keyReleased(KeyEvent e) {pad.keyReleased(e);}
            };
            addKeyListener(listener);
            setFocusable(true);
    }

    private void play() {
        ball.updateBallPosition();
        pad.updatePadPosition();
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        RenderingHints hints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
        ); graphics2D.setRenderingHints(hints);

        //paint background
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, WIDTH, HEIGHT);

        //paint game over line
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(0, 530, WIDTH, 2);

        ball.paint(graphics2D);
        pad.paint(graphics2D);

        graphics2D.dispose();
    }

    private static void runUI(){
        JFrame frame = new JFrame("Wall Tennis");
        frame.add(GAME);
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setBackground(Color.GREEN);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) throws InterruptedException {
        runUI();
        while(true) {
            GAME.play();
            GAME.repaint();
            Thread.sleep(10);
        }
    }
}
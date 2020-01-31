package draw;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;


public class WallTennis extends JPanel {

    private static final WallTennis GAME = new WallTennis();

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

        ball.paint(graphics2D);
        pad.paint(graphics2D);
    }

    private static void runUI(){
        JFrame frame = new JFrame("Wall Tennis");

        frame.add(GAME);
        frame.setSize(400, 600);
        frame.setBackground(Color.GREEN);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
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
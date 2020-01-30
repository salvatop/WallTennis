package draw;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class WallTennis extends JPanel {

    public Ball ball = new Ball(this);
    public Pad pad = new Pad(this);

    private WallTennis() {
        KeyListener listener = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                pad.keyPressed(e);
                System.out.println("keyPressed=" + KeyEvent.getKeyText(e.getKeyCode()));
            }

            @Override
            public void keyReleased(KeyEvent e) {
                pad.keyReleased(e);
                System.out.println("keyReleased=" + KeyEvent.getKeyText(e.getKeyCode()));
            }
        };
        addKeyListener(listener);
        setFocusable(true);
    }

    private void play() {
        ball.updateBallPosition();
        pad.updatePadPosition();
    }

    //@Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics ;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        ball.paint(graphics2D);
        pad.paint(graphics2D);
    }

    public static void main(String[] args) throws InterruptedException {

        WallTennis game = new WallTennis();

        JFrame frame = new JFrame("Wall Tennis");
        frame.add(game);
        frame.setSize(400, 600);
        frame.setBackground(Color.GREEN);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while(true) {
            game.play();
            game.repaint();
            Thread.sleep(10);
        }
    }
}
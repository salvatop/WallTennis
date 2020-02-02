package draw;

import javax.swing.JOptionPane;

public class Score {

    private final WallTennis GAME;

    public Score(WallTennis game) {
        this.GAME = game;
    }

    public void gameOver() {
        System.out.println("Game Over\n");

        JOptionPane.showMessageDialog(null,GAME.getPlayer() + " your record is " + GAME.ball.getRecord()
                + " hits!", "Game Over", JOptionPane.PLAIN_MESSAGE);

        GAME.ball.setHits(0);
        GAME.ball.setBallXPos(0);
        GAME.ball.setBallYPos(0);
        GAME.ball.setBallVelocity(1);
        GAME.ball.setRecord(0);
        GAME.pad.setPadSpeed(2);
    }
}

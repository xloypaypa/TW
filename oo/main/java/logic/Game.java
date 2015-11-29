package logic;

import logic.unit.player.Player;

/**
 * Created by xlo on 15/11/28.
 * it's the game
 */
public class Game {

    private int round;
    private Player playerA, playerB;
    private boolean isEnd;

    public Game(Player playerA, Player playerB) {
        this.round = 0;
        this.isEnd = false;
        this.playerA = playerA;
        this.playerB = playerB;
    }

    public void oneRound() {

    }

    public boolean isEnd() {
        return isEnd;
    }
}

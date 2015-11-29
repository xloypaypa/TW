package logic;

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

    public String nextRound() {
        this.round++;
        if (this.round % 2 == 0) {
            return nowRound(playerA, playerB);
        } else {
            return nowRound(playerB, playerA);
        }
    }

    public boolean isEnd() {
        return isEnd;
    }

    private String nowRound(Player beAttack, Player attacker) {
        String message = beAttack.beAttack(attacker);
        if (beAttack.isDie()) {
            this.isEnd = true;
            return String.format("%s%n%s被打败了", message, beAttack.getName());
        }
        return message;
    }

}

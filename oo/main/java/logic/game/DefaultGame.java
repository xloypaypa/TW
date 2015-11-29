package logic.game;

import logic.unit.player.Player;

/**
 * Created by xlo on 15/11/29.
 * it's the default game
 */
public class DefaultGame extends Game {

    private Player nowAttacker;

    public DefaultGame(Player playerA, Player playerB) {
        super();
        this.players[0] = playerA;
        this.players[1] = playerB;
        this.nowAttacker = playerA;
    }

    @Override
    public boolean isEnd() {
        for (Player player : players) {
            if (!player.isAlive()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Player nowAttacker() {
        return nowAttacker;
    }

    public Player[] playersBeAttack() {
        Player[] result = new Player[1];
        if (nowAttacker == players[0]) {
            result[0] = players[1];
        } else {
            result[0] = players[0];
        }
        return result;
    }

    @Override
    protected void beforeRound() {

    }

    @Override
    protected void whenRound() {
        Player[] playersBeAttack = playersBeAttack();
        for (Player player : playersBeAttack) {
            player.beAttacked(nowAttacker);
        }
    }

    @Override
    protected void afterRound() {
        nowAttacker = playersBeAttack()[0];
    }
}

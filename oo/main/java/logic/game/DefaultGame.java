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
    public void runOneRound() {
        Round round = new DefaultRound(this.nowAttacker, this.playersBeAttack());
        round.startARound();
        nowAttacker = playersBeAttack()[0];
    }

    @Override
    public Player getWinner() throws Exception {
        boolean haveWinner = false;
        for (Player player : players) {
            if (!player.isAlive()) {
                haveWinner = true;
            }
        }
        if (haveWinner) {
            return solveWinner();
        } else {
            return null;
        }
    }

    private Player solveWinner() throws Exception {
        for (Player player : players) {
            if (player.isAlive()) {
                return player;
            }
        }
        throw new Exception(ALL_PLAYER_DIE);
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
}

package logic.game;

import logic.unit.player.Player;

/**
 * Created by xlo on 15/11/29.
 * it's the default game
 */
public class DefaultGame extends Game {

    public DefaultGame(Player playerA, Player playerB) {
        super();
        this.players[0] = playerA;
        this.players[1] = playerB;
    }

    @Override
    public void runOneRound() {
        Round round = new DefaultRound(this.getPlayers());
        round.startARound();
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
}

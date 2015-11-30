package logic.game;

import logic.unit.player.Player;

/**
 * Created by xlo on 15/11/29.
 * it's the interface for game
 */
public abstract class Game {

    public static final String ALL_PLAYER_DIE = "all player die";
    protected Player[] players;

    public Game() {
        this.players = new Player[2];
    }

    public abstract void runOneRound();

    public Player[] getPlayers() {
        return players;
    }

    public abstract Player getWinner() throws Exception;

    public abstract Player nowAttacker();

    public abstract Player[] playersBeAttack();
}

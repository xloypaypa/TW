package logic.game;

import logic.unit.player.Player;

/**
 * Created by xlo on 15/11/29.
 * it's the interface for game
 */
public abstract class Game {

    protected Player[] players;

    public Game() {
        this.players = new Player[2];
    }

    public void runOneRound() {
        beforeRound();
        whenRound();
        afterRound();
    }

    public Player[] getPlayers() {
        return players;
    }

    public abstract boolean isEnd();

    public abstract Player nowAttacker();

    public abstract Player[] playersBeAttack();

    protected abstract void beforeRound();

    protected abstract void whenRound();

    protected abstract void afterRound();
}

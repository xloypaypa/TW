package main;

import logic.game.DefaultGame;
import logic.game.Game;
import logic.log.GameLog;
import logic.unit.player.NormalPlayer;

/**
 * Created by xlo on 15/11/28.
 * it's the main class
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Game game = new DefaultGame(new NormalPlayer("a", 100, 10, GameLog.getGameLog()),
                new NormalPlayer("b", 100, 20, GameLog.getGameLog()));
        while (game.getWinner() == null) {
            game.runOneRound();
        }
        GameLog.getGameLog().showWinner(game.getWinner());
    }
}

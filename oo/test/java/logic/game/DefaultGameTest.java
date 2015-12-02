package logic.game;

import logic.unit.player.NormalPlayer;
import logic.unit.player.Player;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xlo on 15/11/29.
 * it's the testing code for default game
 */
public class DefaultGameTest {

    @Test
    public void game_should_have_winner_when_one_player_not_alive() throws Exception {
        Player playerA = new NormalPlayer("a", 5, 10);
        Player playerB = new NormalPlayer("b", 5, 10);
        DefaultGame defaultGame = new DefaultGame(playerA, playerB);

        defaultGame.runOneRound();

        assertNotNull(defaultGame.getWinner());
    }

    @Test
    public void game_should_not_have_winner_when_two_player_alive() throws Exception {
        Player playerA = new NormalPlayer("a", 100, 10);
        Player playerB = new NormalPlayer("b", 100, 10);
        DefaultGame defaultGame = new DefaultGame(playerA, playerB);

        defaultGame.runOneRound();

        assertNull(defaultGame.getWinner());
    }

    @Test
    public void should_error_when_all_player_die() {
        Player playerA = new NormalPlayer("a", -1, 10);
        Player playerB = new NormalPlayer("b", -1, 10);
        DefaultGame defaultGame = new DefaultGame(playerA, playerB);

        try {
            defaultGame.getWinner();
            fail("not get error");
        } catch (Exception e) {
            assertEquals(Game.ALL_PLAYER_DIE, e.getMessage());
        }
    }
}
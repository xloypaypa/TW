package logic.game;

import logic.unit.player.NormalPlayer;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xlo on 15/11/29.
 * it's the testing code for default game
 */
public class DefaultGameTest {

    @Test
    public void should_playerA_be_the_actor_at_first() {
        NormalPlayer playerA = new NormalPlayer("a", 10, 10);
        NormalPlayer playerB = new NormalPlayer("b", 10, 10);
        DefaultGame defaultGame = new DefaultGame(playerA, playerB);

        assertEquals(playerA, defaultGame.nowAttacker());
    }

    @Test
    public void game_should_have_winner_when_one_player_not_alive() throws Exception {
        NormalPlayer playerA = new NormalPlayer("a", 5, 10);
        NormalPlayer playerB = new NormalPlayer("b", 5, 10);
        DefaultGame defaultGame = new DefaultGame(playerA, playerB);

        defaultGame.runOneRound();

        assertNotNull(defaultGame.getWinner());
    }

    @Test
    public void game_should_not_have_winner_when_two_player_alive() throws Exception {
        NormalPlayer playerA = new NormalPlayer("a", 100, 10);
        NormalPlayer playerB = new NormalPlayer("b", 100, 10);
        DefaultGame defaultGame = new DefaultGame(playerA, playerB);

        defaultGame.runOneRound();

        assertNull(defaultGame.getWinner());
    }

    @Test
    public void should_change_actor_when_one_round_end() {
        NormalPlayer playerA = new NormalPlayer("a", 100, 10);
        NormalPlayer playerB = new NormalPlayer("b", 100, 10);
        DefaultGame defaultGame = new DefaultGame(playerA, playerB);

        defaultGame.runOneRound();

        assertEquals(playerB, defaultGame.nowAttacker());
    }

    @Test
    public void should_error_when_all_player_die() {
        NormalPlayer playerA = new NormalPlayer("a", -1, 10);
        NormalPlayer playerB = new NormalPlayer("b", -1, 10);
        DefaultGame defaultGame = new DefaultGame(playerA, playerB);

        try {
            defaultGame.getWinner();
            fail("not get error");
        } catch (Exception e) {
            assertEquals(Game.ALL_PLAYER_DIE, e.getMessage());
        }
    }

    @Test
    public void playerA_should_be_attacker_at_first_round() {
        NormalPlayer playerA = new NormalPlayer("a", 100, 10);
        NormalPlayer playerB = new NormalPlayer("b", 100, 10);
        DefaultGame defaultGame = new DefaultGame(playerA, playerB);

        assertEquals(playerA, defaultGame.nowAttacker());
    }

    @Test
    public void playerB_should_be_attacker_at_second_round_after_playerB_be_attacked() {
        NormalPlayer playerA = new NormalPlayer("a", 100, 10);
        NormalPlayer playerB = new NormalPlayer("b", 100, 10);
        DefaultGame defaultGame = new DefaultGame(playerA, playerB);

        defaultGame.runOneRound();

        assertEquals(playerB, defaultGame.nowAttacker());
    }

    @Test
    public void should_only_have_one_player_be_attacked() {
        NormalPlayer playerA = new NormalPlayer("a", 10, 10);
        NormalPlayer playerB = new NormalPlayer("b", 10, 10);
        DefaultGame defaultGame = new DefaultGame(playerA, playerB);

        assertEquals(1, defaultGame.playersBeAttack().length);
    }

    @Test
    public void PlayerB_should_is_the_player_except_now_actor() {
        NormalPlayer playerA = new NormalPlayer("a", 10, 10);
        NormalPlayer playerB = new NormalPlayer("b", 10, 10);
        DefaultGame defaultGame = new DefaultGame(playerA, playerB);

        assertEquals(playerB, defaultGame.playersBeAttack()[0]);
    }
}
package logic.game;

import logic.unit.player.NormalPlayer;
import org.junit.Test;
import org.mockito.InOrder;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
    public void game_should_end_when_one_player_not_alive() {
        NormalPlayer playerA = new NormalPlayer("a", 5, 10);
        NormalPlayer playerB = new NormalPlayer("b", 5, 10);
        DefaultGame defaultGame = new DefaultGame(playerA, playerB);

        defaultGame.runOneRound();

        assertTrue(defaultGame.isEnd());
    }

    @Test
    public void game_should_end_when_two_player_alive() {
        NormalPlayer playerA = new NormalPlayer("a", 100, 10);
        NormalPlayer playerB = new NormalPlayer("b", 100, 10);
        DefaultGame defaultGame = new DefaultGame(playerA, playerB);

        defaultGame.runOneRound();

        assertFalse(defaultGame.isEnd());
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
    public void playerB_should_be_attack_at_first_round() {
        NormalPlayer playerA = new NormalPlayer("a", 100, 10);
        NormalPlayer playerB = new NormalPlayer("b", 100, 10);
        playerA = spy(playerA);
        playerB = spy(playerB);
        DefaultGame defaultGame = new DefaultGame(playerA, playerB);

        defaultGame.runOneRound();

        verify(playerB).beAttacked(playerA);
    }

    @Test
    public void playerA_should_be_attack_at_second_round_after_playerB_be_attacked() {
        NormalPlayer playerA = new NormalPlayer("a", 100, 10);
        NormalPlayer playerB = new NormalPlayer("b", 100, 10);
        playerA = spy(playerA);
        playerB = spy(playerB);
        InOrder attackOrder = inOrder(playerA, playerB);
        DefaultGame defaultGame = new DefaultGame(playerA, playerB);

        defaultGame.runOneRound();
        defaultGame.runOneRound();

        attackOrder.verify(playerB).beAttacked(playerA);
        attackOrder.verify(playerA).beAttacked(playerB);
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
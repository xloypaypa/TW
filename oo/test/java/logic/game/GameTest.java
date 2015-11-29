package logic.game;

import logic.unit.player.Player;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;

/**
 * Created by xlo on 15/11/29.
 * it's the testing code for game
 */
public class GameTest {

    private Game game;
    private InOrder order;

    @Before
    public void setUp() throws Exception {
        game = spy(new Game() {
            @Override
            public Player getWinner() throws Exception {
                return null;
            }

            @Override
            public Player nowAttacker() {
                return null;
            }

            @Override
            public Player[] playersBeAttack() {
                return new Player[0];
            }

            @Override
            protected void beforeRound() {

            }

            @Override
            protected void whenRound() {

            }

            @Override
            protected void afterRound() {

            }
        });
        order = inOrder(game);
    }

    @Test
    public void round_should_in_order_as_before_when_after() {
        game.runOneRound();

        order.verify(game).beforeRound();
        order.verify(game).whenRound();
        order.verify(game).afterRound();
    }
}
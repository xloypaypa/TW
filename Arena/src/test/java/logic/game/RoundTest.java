package logic.game;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;

/**
 * Created by xlo on 15/11/30.
 * it's the testing code for round
 */
public class RoundTest {

    private Round round;
    private InOrder order;

    @Before
    public void setUp() {
        this.round = spy(new Round(null) {
            @Override
            protected Round.RoundStatus whenRoundStart() {
                return RoundStatus.ACTION_START;
            }

            @Override
            protected Round.RoundStatus whenActionStart() {
                return RoundStatus.ACTION;
            }

            @Override
            protected Round.RoundStatus whenAction() {
                return RoundStatus.ACTION_END;
            }

            @Override
            protected Round.RoundStatus whenActionEnd() {
                return RoundStatus.ROUND_END;
            }

            @Override
            protected Round.RoundStatus whenRoundEnd() {
                return null;
            }
        });
        this.order = inOrder(round);
    }

    @Test
    public void checkRoundLifeCircle() {
        round.startARound();
        order.verify(round).whenRoundStart();
        order.verify(round).whenActionStart();
        order.verify(round).whenAction();
        order.verify(round).whenActionEnd();
        order.verify(round).whenRoundEnd();
    }

}
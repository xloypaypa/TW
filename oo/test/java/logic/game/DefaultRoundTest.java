package logic.game;

import logic.log.GameLog;
import logic.unit.player.NormalPlayer;
import logic.unit.player.Player;
import org.junit.Test;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Created by xlo on 15/11/30.
 * it's the testing code for default round
 */
public class DefaultRoundTest {

    @Test
    public void defender_should_be_attack_when_action() {
        NormalPlayer playerA = new NormalPlayer("a", 100, 10, GameLog.getGameLog());
        NormalPlayer playerB = new NormalPlayer("b", 100, 10, GameLog.getGameLog());
        playerA = spy(playerA);
        playerB = spy(playerB);
        DefaultRound defaultGame = new DefaultRound(playerA, new Player[]{playerB});

        defaultGame.startARound();

        verify(playerB).beAttacked(playerA);
    }
}
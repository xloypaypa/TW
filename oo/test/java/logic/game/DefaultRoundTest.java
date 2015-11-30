package logic.game;

import logic.buff.BuffPackage;
import logic.log.GameLog;
import logic.unit.player.NormalPlayer;
import logic.unit.player.Player;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by xlo on 15/11/30.
 * it's the testing code for default round
 */
public class DefaultRoundTest {

    @Test
    public void defender_should_be_attack_when_action() {
        NormalPlayer playerA = new NormalPlayer("a", 100, 10);
        NormalPlayer playerB = new NormalPlayer("b", 100, 10);
        playerA = spy(playerA);
        playerB = spy(playerB);

        BuffPackage buffPackage = new BuffPackage();
        when(playerA.getAttack()).thenReturn(buffPackage);

        DefaultRound defaultRound = new DefaultRound(playerA, new Player[]{playerB}, GameLog.getGameLog());

        defaultRound.whenAction();

        verify(playerB).attachBuff(buffPackage);
    }

    @Test
    public void defender_should_clear_buff_when_action_end() {
        NormalPlayer playerA = new NormalPlayer("a", 100, 10);
        NormalPlayer playerB = new NormalPlayer("b", 100, 10);
        playerA = spy(playerA);
        playerB = spy(playerB);

        BuffPackage buffPackage = new BuffPackage();
        when(playerA.getAttack()).thenReturn(buffPackage);

        DefaultRound defaultRound = new DefaultRound(playerA, new Player[]{playerB}, GameLog.getGameLog());
        defaultRound.buffPackage = new BuffPackage();

        defaultRound.whenActionEnd();

        verify(playerB).immediatelyBuffToAttribute();
    }

    @Test
    public void should_save_players_hp_when_action_start() {
        NormalPlayer playerA = new NormalPlayer("a", 100, 10);
        NormalPlayer playerB = new NormalPlayer("b", 100, 10);

        DefaultRound defaultRound = new DefaultRound(playerA, new Player[]{playerB}, GameLog.getGameLog());

        defaultRound.whenActionStart();

        assertEquals(100, defaultRound.defenderOldHp[0], 1e-3);
    }

    @Test
    public void should_write_log_when_action_end() {
        NormalPlayer playerA = new NormalPlayer("a", 100, 10);
        NormalPlayer playerB = new NormalPlayer("b", 100, 10);
        playerA = spy(playerA);
        playerB = spy(playerB);

        BuffPackage buffPackage = new BuffPackage();
        when(playerA.getAttack()).thenReturn(buffPackage);

        GameLog gameLog = spy(GameLog.getGameLog());
        DefaultRound defaultRound = new DefaultRound(playerA, new Player[]{playerB}, gameLog);

        defaultRound.whenActionStart();
        defaultRound.whenAction();
        defaultRound.whenActionEnd();

        verify(gameLog).afterPlayerBeAttacked(playerB.getName(), playerB.getJobName(), 0, 100, playerA, false);
    }
}
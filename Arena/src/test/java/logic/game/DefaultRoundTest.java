package logic.game;

import logic.attribute.AttributeType;
import logic.buff.*;
import logic.log.GameLog;
import logic.unit.player.NormalPlayer;
import logic.unit.player.Player;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by xlo on 15/11/30.
 * it's the testing code for default round
 */
public class DefaultRoundTest {

    @Test
    public void defender_should_be_attack_when_action() {
        Player playerA = NormalPlayer.getNewNormalPlayer("a", 100, 10);
        Player playerB = NormalPlayer.getNewNormalPlayer("b", 100, 10);
        playerA = spy(playerA);
        playerB = spy(playerB);

        BuffPackage buffPackage = new BuffPackage();
        when(playerA.getAttack()).thenReturn(buffPackage);

        DefaultRound defaultRound = new DefaultRound(new Player[]{playerA, playerB}, GameLog.getGameLog());

        defaultRound.whenActionStart();
        defaultRound.whenAction();

        verify(playerB).attachBuff(buffPackage);
    }

    @Test
    public void should_save_players_hp_when_round_start() {
        Player playerA = NormalPlayer.getNewNormalPlayer("a", 100, 10);
        Player playerB = NormalPlayer.getNewNormalPlayer("b", 100, 10);

        DefaultRound defaultRound = new DefaultRound(new Player[]{playerA, playerB}, GameLog.getGameLog());

        defaultRound.whenActionStart();

        assertEquals(100, defaultRound.playerOldHp[0], 1e-3);
    }

    @Test
    public void should_write_log_when_action_end() {
        Player playerA = NormalPlayer.getNewNormalPlayer("a", 100, 10);
        Player playerB = NormalPlayer.getNewNormalPlayer("b", 100, 10);
        playerA = spy(playerA);
        playerB = spy(playerB);

        BuffPackage buffPackage = new BuffPackage();
        when(playerA.getAttack()).thenReturn(buffPackage);

        GameLog gameLog = spy(GameLog.getGameLog());
        DefaultRound defaultRound = new DefaultRound(new Player[]{playerA, playerB}, gameLog);

        defaultRound.whenRoundStart();
        defaultRound.whenActionStart();
        defaultRound.whenAction();
        defaultRound.whenActionEnd();

        verify(gameLog).afterPlayerBeAttacked(playerB.getName(), playerB.getJobName(), 0, 100, playerA, buffPackage);
    }

    @Test
    public void should_go_to_round_end_when_attacker_is_dizzy() {
        Player playerA = NormalPlayer.getNewNormalPlayer("a", 100, 10);
        Player playerB = NormalPlayer.getNewNormalPlayer("b", 100, 10);

        BuffPackage buffPackage = new BuffPackage();
        buffPackage.addContinueBuff(new DizzyBuff(3));
        playerA.attachBuff(buffPackage);

        DefaultRound defaultRound = spy(new DefaultRound(new Player[]{playerA, playerB}, GameLog.getGameLog()));
        assertEquals(Round.RoundStatus.ACTION_END, defaultRound.whenActionStart());
    }

    @Test
    public void should_go_to_action_end_at_3rd_round_when_attacker_is_cold() {
        Player playerA = NormalPlayer.getNewNormalPlayer("a", 100, 10);
        Player playerB = NormalPlayer.getNewNormalPlayer("b", 100, 10);

        BuffPackage buffPackage = new BuffPackage();
        buffPackage.addContinueBuff(new ColdBuff(3));
        playerA.attachBuff(buffPackage);

        DefaultRound defaultRound = spy(new DefaultRound(new Player[]{playerA, playerB}, GameLog.getGameLog()));
        defaultRound.startARound();
        defaultRound = spy(new DefaultRound(new Player[]{playerA, playerB}, GameLog.getGameLog()));
        defaultRound.startARound();
        defaultRound = spy(new DefaultRound(new Player[]{playerA, playerB}, GameLog.getGameLog()));
        assertEquals(Round.RoundStatus.ACTION_END, defaultRound.whenActionStart());
    }

    @Test
    public void should_get_hurt_when_round_start_if_have_fire_buff() {
        Player playerA = NormalPlayer.getNewNormalPlayer("a", 100, 10);
        Player playerB = NormalPlayer.getNewNormalPlayer("b", 100, 10);

        BuffPackage buffPackage = new BuffPackage();
        buffPackage.addContinueBuff(new FireBuff(3, 1));
        playerB.attachBuff(buffPackage);

        DefaultRound defaultRound = new DefaultRound(new Player[]{playerA, playerB}, GameLog.getGameLog());
        defaultRound.whenRoundStart();
        assertEquals(99, playerB.getAttribute().getAttribute(AttributeType.HP), 1e-3);
    }
}
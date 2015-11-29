package logic.unit.player;

import logic.log.GameLog;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Created by xlo on 15/11/29.
 * it's the testing code for normal player
 */
public class NormalPlayerTest {

    @Test
    public void should_a_normal_player_who_have_100_hp_remain_90_hp_when_be_attacked_by_item_who_have_10_attack_unit() {
        NormalPlayer normalPlayer = new NormalPlayer("abc", 100, 100, GameLog.getGameLog());
        normalPlayer.beAttacked(new NormalPlayer("", 10, 10, GameLog.getGameLog()));
        assertEquals(90, normalPlayer.getHp(), 1e-3);
    }

    @Test
    public void should_alive_when_normal_player_have_10_hp() {
        NormalPlayer normalPlayer = new NormalPlayer("abc", 10, 10, GameLog.getGameLog());
        assertTrue(normalPlayer.isAlive());
    }

    @Test
    public void should_alive_when_normal_player_have_0_hp() {
        NormalPlayer normalPlayer = new NormalPlayer("abc", 0, 0, GameLog.getGameLog());
        assertTrue(normalPlayer.isAlive());
    }

    @Test
    public void should_not_alive_when_normal_player_have_hp_less_than_zero() {
        NormalPlayer normalPlayer = new NormalPlayer("abc", -1, -1, GameLog.getGameLog());
        assertFalse(normalPlayer.isAlive());
    }

    @Test
    public void should_write_log_when_be_attack() {
        GameLog gameLog = spy(GameLog.getGameLog());
        NormalPlayer normalPlayer = new NormalPlayer("abc", 100, 100, gameLog);
        Player player = new NormalPlayer("", 10, 10, GameLog.getGameLog());
        normalPlayer.beAttacked(player);
        verify(gameLog).afterPlayerBeAttacked(normalPlayer, player);
    }
}
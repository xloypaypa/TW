package logic.unit.player;

import logic.job.Job;
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
    public void normal_player_job_should_be_normal() {
        assertEquals(Job.normal, new NormalPlayer("a", 10, 10).getJobName());
    }

    @Test
    public void should_alive_when_normal_player_have_10_hp() {
        NormalPlayer normalPlayer = new NormalPlayer("abc", 10, 10);
        assertTrue(normalPlayer.isAlive());
    }

    @Test
    public void should_alive_when_normal_player_have_0_hp() {
        NormalPlayer normalPlayer = new NormalPlayer("abc", 0, 0);
        assertTrue(normalPlayer.isAlive());
    }

    @Test
    public void should_not_alive_when_normal_player_have_hp_less_than_zero() {
        NormalPlayer normalPlayer = new NormalPlayer("abc", -1, -1);
        assertFalse(normalPlayer.isAlive());
    }

    @Test
    public void should_clear_immediately_buff_when_calculate_buff() {
        NormalPlayer normalPlayer = new NormalPlayer("a", 1, 1);
        normalPlayer.buffPackage = spy(normalPlayer.buffPackage);
        normalPlayer.buffToAttribute();
        verify(normalPlayer.buffPackage).clearImmediatelyBuff();
    }

}
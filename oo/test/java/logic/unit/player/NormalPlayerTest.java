package logic.unit.player;

import logic.attribute.AttributeType;
import logic.buff.BuffPackage;
import logic.job.Job;
import logic.unit.item.weapon.impl.NormalLuckyWeapon;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
        Player normalPlayer = new NormalPlayer("abc", 10, 10);
        assertTrue(normalPlayer.isAlive());
    }

    @Test
    public void should_alive_when_normal_player_have_0_hp() {
        Player normalPlayer = new NormalPlayer("abc", 0, 0);
        assertTrue(normalPlayer.isAlive());
    }

    @Test
    public void should_not_alive_when_normal_player_have_hp_less_than_zero() {
        Player normalPlayer = new NormalPlayer("abc", -1, -1);
        assertFalse(normalPlayer.isAlive());
    }

    @Test
    public void should_clear_immediately_buff_when_calculate_buff() {
        NormalPlayer normalPlayer = new NormalPlayer("a", 1, 1);
        normalPlayer.buffPackage = spy(normalPlayer.buffPackage);
        normalPlayer.immediatelyBuffToAttribute();
        verify(normalPlayer.buffPackage).clearImmediatelyBuff();
    }

    @Test
    public void should_only_get_direct_attack_of_weapon() throws Exception {
        NormalPlayer normalPlayer = new NormalPlayer("a", 1, 1);
        Random random = mock(Random.class);
        when(random.nextInt()).thenReturn(100);
        normalPlayer.setWeapon(new NormalLuckyWeapon(1, random));
        BuffPackage buffPackage = normalPlayer.getAttack();
        assertEquals(0, buffPackage.getImmediatelyEffect().getAttribute(AttributeType.LUCK), 1e-3);
    }

}
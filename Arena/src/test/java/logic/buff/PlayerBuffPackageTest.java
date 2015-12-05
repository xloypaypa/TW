package logic.buff;

import logic.attribute.Attribute;
import logic.attribute.AttributeType;
import logic.buff.buffPackage.BuffFromMessage;
import logic.buff.buffPackage.PlayerBuffPackage;
import logic.log.GameLog;
import logic.unit.player.NormalPlayer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Created by xlo on 2015/12/4.
 * it's the testing code for player buff package
 */
public class PlayerBuffPackageTest {

    @Test
    public void should_clear_immediately_buff_after_calculate_immediately_to_Attribute() {
        NormalPlayer normalPlayer = NormalPlayer.getNewNormalPlayer("a", 1, 2);
        PlayerBuffPackage playerBuffPackage = spy(new PlayerBuffPackage(normalPlayer, GameLog.getGameLog()));
        playerBuffPackage.calculateUserImmediatelyBuff();
        verify(playerBuffPackage).clearImmediatelyBuff();
    }

    @Test
    public void should_clear_continue_buff_after_calculate_continue_to_Attribute() {
        NormalPlayer normalPlayer = NormalPlayer.getNewNormalPlayer("a", 1, 2);
        PlayerBuffPackage playerBuffPackage = spy(new PlayerBuffPackage(normalPlayer, GameLog.getGameLog()));
        playerBuffPackage.calculateUserContinueBuff();
        verify(playerBuffPackage).clearContinueBuff();
    }

    @Test
    public void should_only_calculate_attribute_in_list() {
        NormalPlayer normalPlayer = NormalPlayer.getNewNormalPlayer("a", 1, 2);
        PlayerBuffPackage playerBuffPackage = spy(new PlayerBuffPackage(normalPlayer, GameLog.getGameLog()));
        playerBuffPackage.addContinueBuff(new BuffFromMessage(), new AbstractContinueBuff(1) {
            @Override
            public Attribute getEffect() {
                Attribute attribute = new Attribute();
                attribute.setAttribute(AttributeType.HP, 1);
                return attribute;
            }
        });
        playerBuffPackage.calculateUserContinueBuff();
        assertEquals(1, normalPlayer.getAttribute().getAttribute(AttributeType.HP), 1e-3);
    }

    @Test
    public void should_change_hp_after_calculate_continue_to_Attribute() {
        NormalPlayer normalPlayer = NormalPlayer.getNewNormalPlayer("a", 1, 2);
        PlayerBuffPackage playerBuffPackage = spy(new PlayerBuffPackage(normalPlayer, GameLog.getGameLog()));
        playerBuffPackage.addContinueBuff(new BuffFromMessage(), new FireBuff(1, 1));
        playerBuffPackage.calculateUserContinueBuff();
        assertEquals(0, normalPlayer.getAttribute().getAttribute(AttributeType.HP), 1e-3);
    }

    @Test
    public void should_false_when_check_user_can_attack_with_dizzy_buff() {
        NormalPlayer normalPlayer = NormalPlayer.getNewNormalPlayer("a", 1, 2);
        PlayerBuffPackage playerBuffPackage = spy(new PlayerBuffPackage(normalPlayer, GameLog.getGameLog()));
        playerBuffPackage.addContinueBuff(new BuffFromMessage(), new DizzyBuff(1));
        assertFalse(playerBuffPackage.checkUserCanAttack());
    }

    @Test
    public void should_not_have_dizzy_buff_when_check_can_attack() {
        NormalPlayer normalPlayer = NormalPlayer.getNewNormalPlayer("a", 1, 2);
        PlayerBuffPackage playerBuffPackage = spy(new PlayerBuffPackage(normalPlayer, GameLog.getGameLog()));
        playerBuffPackage.addContinueBuff(new BuffFromMessage(), new DizzyBuff(1));
        playerBuffPackage.checkUserCanAttack();
        assertEquals(0, playerBuffPackage.getContinueBuffs().size());
    }

    @Test
    public void should_get_cold_effort_when_check_can_attack_at_3rd_time() {
        NormalPlayer normalPlayer = NormalPlayer.getNewNormalPlayer("a", 1, 2);
        PlayerBuffPackage playerBuffPackage = spy(new PlayerBuffPackage(normalPlayer, GameLog.getGameLog()));
        playerBuffPackage.addContinueBuff(new BuffFromMessage(), new ColdBuff(5));
        assertTrue(playerBuffPackage.checkUserCanAttack());
        assertTrue(playerBuffPackage.checkUserCanAttack());
        assertFalse(playerBuffPackage.checkUserCanAttack());
    }

}
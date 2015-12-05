package logic.buff;

import logic.attribute.AttributeType;
import logic.buff.buffPackage.BuffFromMessage;
import logic.buff.buffPackage.BuffPackage;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xlo on 15/11/29.
 * it's the testing code for buff package
 */
public class BuffPackageTest {

    @Test
    public void should_have_10_attack_when_have_20_attack_and_10_defence_before() {
        BuffPackage buffPackage = new BuffPackage();
        buffPackage.addImmediatelyBuff(new BuffFromMessage(), new NormalAttackBuff(10));
        buffPackage.addImmediatelyBuff(new BuffFromMessage(), new NormalAttackBuff(10));
        buffPackage.addImmediatelyBuff(new BuffFromMessage(), new DefenceBuff(10));
        assertEquals(-10, buffPackage.getImmediatelyEffect().getAttribute(AttributeType.HP), 1e-3);
    }

    @Test
    public void should_continue_buff_get_only_one_buff_when_add_same_buff() throws Exception {
        BuffPackage buffPackage = new BuffPackage();
        buffPackage.addContinueBuff(new BuffFromMessage(), new FireBuff(1, 1));
        buffPackage.addContinueBuff(new BuffFromMessage(), new FireBuff(1, 1));

        assertEquals(1, buffPackage.getContinueBuffs().size());
    }

    @Test
    public void should_continue_buff_add_round_when_add_same_buff() throws Exception {
        BuffPackage buffPackage = new BuffPackage();
        buffPackage.addContinueBuff(new BuffFromMessage(), new FireBuff(1, 1));
        buffPackage.addContinueBuff(new BuffFromMessage(), new FireBuff(1, 1));

        assertEquals(2, buffPackage.getContinueBuffs().get(0).remainRound());
    }

    @Test
    public void should_continue_buff_get_only_one_buff_when_add_same_buff_in_package() throws Exception {
        BuffPackage buffPackage = new BuffPackage();
        BuffPackage other = new BuffPackage();

        buffPackage.addContinueBuff(new BuffFromMessage(), new FireBuff(1, 1));
        other.addContinueBuff(new BuffFromMessage(), new FireBuff(1, 1));
        buffPackage.addBuffPackage(other);

        assertEquals(1, buffPackage.getContinueBuffs().size());
    }

    @Test
    public void should_continue_buff_add_round_when_add_same_buff_in_package() throws Exception {
        BuffPackage buffPackage = new BuffPackage();
        BuffPackage other = new BuffPackage();

        buffPackage.addContinueBuff(new BuffFromMessage(), new FireBuff(1, 1));
        other.addContinueBuff(new BuffFromMessage(), new FireBuff(1, 1));
        buffPackage.addBuffPackage(other);

        assertEquals(2, buffPackage.getContinueBuffs().get(0).remainRound());
    }

    @Test
    public void should_be_clear_when_buff_not_have_round() throws Exception {
        BuffPackage buffPackage = new BuffPackage();
        buffPackage.addContinueBuff(new BuffFromMessage(), new FireBuff(1, 1));
        buffPackage.addContinueBuff(new BuffFromMessage(), new FireBuff(1, 1));

        buffPackage.getContinueBuffs().get(0).effected();
        buffPackage.getContinueBuffs().get(0).effected();
        buffPackage.clearContinueBuff();

        assertEquals(0, buffPackage.getContinueBuffs().size());
    }

    @Test
    public void should_not_be_clear_when_buff_have_round() throws Exception {
        BuffPackage buffPackage = new BuffPackage();
        buffPackage.addContinueBuff(new BuffFromMessage(), new FireBuff(1, 1));
        buffPackage.addContinueBuff(new BuffFromMessage(), new FireBuff(1, 1));

        buffPackage.getContinueBuffs().get(0).effected();
        buffPackage.clearContinueBuff();

        assertEquals(1, buffPackage.getContinueBuffs().size());
    }

    @Test
    public void should_have_all_attribute_in_continue_buffs() {
        BuffPackage buffPackage = new BuffPackage();
        buffPackage.addContinueBuff(new BuffFromMessage(), new FireBuff(1, 1));
        buffPackage.addContinueBuff(new BuffFromMessage(), new FireBuff(1, 1));
        buffPackage.addContinueBuff(new BuffFromMessage(), new ColdBuff(1));

        assertEquals(1, buffPackage.getContinueEffect().getAttribute(AttributeType.COLD), 1e-3);
        assertEquals(1, buffPackage.getContinueEffect().getAttribute(AttributeType.FIRE), 1e-3);
    }

    @Test
    public void should_not_have_continue_buff_when_effect_all_and_clear() {
        BuffPackage buffPackage = new BuffPackage();
        buffPackage.addContinueBuff(new BuffFromMessage(), new FireBuff(1, 1));
        buffPackage.addContinueBuff(new BuffFromMessage(), new ColdBuff(1));

        buffPackage.effectAllContinueBuff();
        buffPackage.clearContinueBuff();

        assertEquals(0, buffPackage.getContinueBuffs().size());
    }

    @Test
    public void should_get_no_data_when_get_different_attribute() {
        BuffPackage buffPackage = new BuffPackage();
        buffPackage.addContinueBuff(new BuffFromMessage(), new FireBuff(1, 1));

        assertEquals(0, buffPackage.getContinueBuffsWith(AttributeType.COLD).size());
    }

    @Test
    public void should_get_buff_when_get_same_attribute() {
        BuffPackage buffPackage = new BuffPackage();
        FireBuff buff = new FireBuff(1, 1);
        buffPackage.addContinueBuff(new BuffFromMessage(), buff);

        assertEquals(buff, buffPackage.getContinueBuffsWith(AttributeType.FIRE).get(0));
    }

    @Test
    public void should_only_get_buff_with_same_attribute() {
        BuffPackage buffPackage = new BuffPackage();
        FireBuff buff = new FireBuff(1, 1);
        buffPackage.addContinueBuff(new BuffFromMessage(), buff);
        buffPackage.addContinueBuff(new BuffFromMessage(), new ColdBuff(1));

        assertEquals(1, buffPackage.getContinueBuffsWith(AttributeType.FIRE).size());
        assertEquals(buff, buffPackage.getContinueBuffsWith(AttributeType.FIRE).get(0));
    }

}
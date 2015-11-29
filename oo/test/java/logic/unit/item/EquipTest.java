package logic.unit.item;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xlo on 15/11/29.
 * it's the testing code for equip
 */
public class EquipTest {

    @Test
    public void equip_with_10_defence_should_get_10_hurting_when_attack_by_20_attack() {
        assertEquals(10, new Equip("", 10).hurtingAfterDefence(() -> 20), 1e-3);
    }

    @Test
    public void equip_with_100_defence_should_get_0_hurting_when_attack_by_20_attack() {
        assertEquals(0, new Equip("", 100).hurtingAfterDefence(() -> 20), 1e-3);
    }

}
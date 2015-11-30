package logic.unit.item;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xlo on 15/11/29.
 * it's the testing code for weapon
 */
public class WeaponTest {

    @Test
    public void testBuff() {
        Weapon weapon = new Weapon("a", 10);
        assertEquals(10, weapon.getAttack(), 1e-3);
    }

}
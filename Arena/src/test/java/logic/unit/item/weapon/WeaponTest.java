package logic.unit.item.weapon;

import logic.attribute.AttributeType;
import logic.unit.item.weapon.Weapon;
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
        assertEquals(-10, weapon.getAttack().getImmediatelyEffect().getAttribute(AttributeType.HP), 1e-3);
    }

}
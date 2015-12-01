package logic.unit.player;

import logic.attribute.AttributeType;
import logic.unit.item.weapon.Weapon;
import logic.unit.item.weapon.impl.NormalDizzyWeapon;
import logic.unit.item.weapon.impl.NormalLuckyWeapon;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by xlo on 15/11/29.
 * it's the testing code for solider
 */
public class SoliderPlayerTest {

    @Test
    public void should_have_more_attack_when_have_weapon() throws Exception {
        SoliderPlayer soliderPlayer = new SoliderPlayer("a", 100, 10);
        soliderPlayer.setWeapon(new NormalDizzyWeapon(10, new Random()));
        assertEquals(-20, soliderPlayer.getAttack().getImmediatelyEffect().getAttribute(AttributeType.HP), 1e-3);
    }

    @Test
    public void should_have_same_attack_when_not_have_weapon() {
        SoliderPlayer soliderPlayer = new SoliderPlayer("a", 100, 10);
        assertEquals(-10, soliderPlayer.getAttack().getImmediatelyEffect().getAttribute(AttributeType.HP), 1e-3);
    }
}
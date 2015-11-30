package logic.unit.player;

import logic.attribute.AttributeType;
import logic.log.GameLog;
import logic.unit.item.weapon.Weapon;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by xlo on 15/11/29.
 * it's the testing code for solider
 */
public class SoliderPlayerTest {

    @Test
    public void should_have_more_attack_when_have_weapon() {
        SoliderPlayer soliderPlayer = new SoliderPlayer("a", 100, 10, GameLog.getGameLog());
        soliderPlayer.setWeapon(new Weapon("a", 10));
        assertEquals(-20, soliderPlayer.getAttack().getImmediatelyEffect().getAttribute(AttributeType.HP), 1e-3);
    }

    @Test
    public void should_have_same_attack_when_not_have_weapon() {
        SoliderPlayer soliderPlayer = new SoliderPlayer("a", 100, 10, GameLog.getGameLog());
        assertEquals(-10, soliderPlayer.getAttack().getImmediatelyEffect().getAttribute(AttributeType.HP), 1e-3);
    }
}
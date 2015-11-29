package logic.unit.player;

import logic.log.GameLog;
import logic.unit.item.Equip;
import logic.unit.item.Weapon;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xlo on 15/11/29.
 * it's the testing code for solider
 */
public class SoliderPlayerTest {

    @Test
    public void should_get_less_hurt_when_have_equip() {
        SoliderPlayer soliderPlayer = new SoliderPlayer("a", 100, 10, GameLog.getGameLog());
        soliderPlayer.setEquip(new Equip("a", 10));
        soliderPlayer.beAttacked(new NormalPlayer("a", 10, 10, GameLog.getGameLog()));
        assertEquals(100, soliderPlayer.getHp(), 1e-3);
    }

    @Test
    public void should_get_same_hurt_when_not_have_equip() {
        SoliderPlayer soliderPlayer = new SoliderPlayer("a", 100, 10, GameLog.getGameLog());
        soliderPlayer.beAttacked(new NormalPlayer("a", 10, 10, GameLog.getGameLog()));
        assertEquals(90, soliderPlayer.getHp(), 1e-3);
    }

    @Test
    public void should_have_more_attack_when_have_weapon() {
        SoliderPlayer soliderPlayer = new SoliderPlayer("a", 100, 10, GameLog.getGameLog());
        soliderPlayer.setWeapon(new Weapon("a", 10));
        assertEquals(20, soliderPlayer.getAttack(), 1e-3);
    }

    @Test
    public void should_have_same_attack_when_not_have_weapon() {
        SoliderPlayer soliderPlayer = new SoliderPlayer("a", 100, 10, GameLog.getGameLog());
        assertEquals(10, soliderPlayer.getAttack(), 1e-3);
    }
}
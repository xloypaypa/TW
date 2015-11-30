package logic.log;

import logic.attribute.AttributeType;
import logic.unit.item.Equip;
import logic.unit.item.Weapon;
import logic.unit.player.NormalPlayer;
import logic.unit.player.SoliderPlayer;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xlo on 15/11/29.
 * it's the testing code for game log
 */
public class GameLogTest {

    @Test
    public void testBuildAttackMessage() throws Exception {
        final NormalPlayer a = new NormalPlayer("a", 10, 10);
        assertEquals("普通人b攻击了普通人a,对a造成了10点伤害,a剩余10点生命值.",
                GameLog.getGameLog().buildAttackMessage(
                        a.getName(), a.getJobName(), 10, a.getAttribute().getAttribute(AttributeType.HP), new NormalPlayer("b", 10, 10)));
    }

    @Test
    public void testBuildSoliderWithWeaponAttackMessage() throws Exception {
        SoliderPlayer attacker = new SoliderPlayer("b", 10, 10, GameLog.getGameLog());
        attacker.setWeapon(new Weapon("c", 0));
        final NormalPlayer a = new NormalPlayer("a", 10, 10);
        assertEquals("战士b用c攻击了普通人a,对a造成了10点伤害,a剩余10点生命值.",
                GameLog.getGameLog().buildAttackMessage(
                        a.getName(), a.getJobName(), 10, a.getAttribute().getAttribute(AttributeType.HP), attacker));
    }

    @Test
    public void testBuildSoliderWithEquipAttackMessage() throws Exception {
        SoliderPlayer soliderPlayer = new SoliderPlayer("b", 10, 10, GameLog.getGameLog());
        soliderPlayer.setEquip(new Equip("c", 10));
        assertEquals("普通人a攻击了战士b,对b造成了0点伤害,b剩余10点生命值.",
                GameLog.getGameLog().buildAttackMessage(
                        soliderPlayer.getName(), soliderPlayer.getJobName(), 0, soliderPlayer.getAttribute().getAttribute(AttributeType.HP), new NormalPlayer("a", 10, 10)));
    }

    @Test
    public void testBuildSoliderWithoutWeaponAttackMessage() throws Exception {
        SoliderPlayer attacker = new SoliderPlayer("b", 10, 10, GameLog.getGameLog());
        final NormalPlayer a = new NormalPlayer("a", 10, 10);
        assertEquals("战士b攻击了普通人a,对a造成了10点伤害,a剩余10点生命值.",
                GameLog.getGameLog().buildAttackMessage(
                        a.getName(), a.getJobName(), 10, a.getAttribute().getAttribute(AttributeType.HP), attacker));
    }

    @Test
    public void testBuildWinnerMessage() throws Exception {
        assertEquals("a胜利了.", GameLog.getGameLog().buildWinnerMessage(new NormalPlayer("a", 10, 10)));
    }
}
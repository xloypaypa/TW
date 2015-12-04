package logic.log;

import logic.attribute.AttributeType;
import logic.buff.BuffPackage;
import logic.buff.DizzyBuff;
import logic.buff.LuckBuff;
import logic.game.DefaultGame;
import logic.game.Game;
import logic.unit.item.Equip;
import logic.unit.item.weapon.impl.LongFireWeapon;
import logic.unit.item.weapon.impl.NormalLuckyWeapon;
import logic.unit.player.*;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by xlo on 15/11/29.
 * it's the testing code for game log
 */
public class GameLogTest {

    @Test
    public void testBuildAttackMessage() throws Exception {
        final Player a = NormalPlayer.getNewNormalPlayer("a", 10, 10);
        assertEquals("普通人b攻击了普通人a,对a造成了10点伤害,a剩余10点生命值.",
                GameLog.getGameLog().buildAttackMessage(
                        a.getName(), a.getJobName(), 10, a.getAttribute().getAttribute(AttributeType.HP), NormalPlayer.getNewNormalPlayer("b", 10, 10), new BuffPackage()));
    }

    @Test
    public void testBuildSoliderWithWeaponAttackMessage() throws Exception {
        SoliderPlayer attacker = SoliderPlayer.getNewSoliderPlayer("b", 10, 10);
        attacker.setWeapon(new NormalLuckyWeapon(0, new Random()));
        final Player a = NormalPlayer.getNewNormalPlayer("a", 10, 10);
        assertEquals("战士b用normal lucky攻击了普通人a,对a造成了10点伤害,a剩余10点生命值.",
                GameLog.getGameLog().buildAttackMessage(
                        a.getName(), a.getJobName(), 10, a.getAttribute().getAttribute(AttributeType.HP), attacker, new BuffPackage()));
    }

    @Test
    public void testBuildSoliderWithEquipAttackMessage() throws Exception {
        SoliderPlayer soliderPlayer = SoliderPlayer.getNewSoliderPlayer("b", 10, 10);
        soliderPlayer.setEquip(new Equip("c", 10));
        assertEquals("普通人a攻击了战士b,对b造成了0点伤害,b剩余10点生命值.",
                GameLog.getGameLog().buildAttackMessage(
                        soliderPlayer.getName(), soliderPlayer.getJobName(), 0,
                        soliderPlayer.getAttribute().getAttribute(AttributeType.HP), NormalPlayer.getNewNormalPlayer("a", 10, 10),
                        new BuffPackage()));
    }

    @Test
    public void should_every_job_can_use_weapon() throws Exception {
        PaladinPlayer attacker = PaladinPlayer.getNewPaladinPlayer("b", 10, 10);
        attacker.setWeapon(new LongFireWeapon(0, new Random()));
        final Player a = NormalPlayer.getNewNormalPlayer("a", 10, 10);
        assertEquals("圣骑士b用long fire攻击了普通人a,对a造成了10点伤害,a剩余10点生命值.",
                GameLog.getGameLog().buildAttackMessage(
                        a.getName(), a.getJobName(), 10, a.getAttribute().getAttribute(AttributeType.HP), attacker, new BuffPackage()));
    }

    @Test
    public void testBuildSoliderWithoutWeaponAttackMessage() throws Exception {
        SoliderPlayer attacker = SoliderPlayer.getNewSoliderPlayer("b", 10, 10);
        final Player a = NormalPlayer.getNewNormalPlayer("a", 10, 10);
        assertEquals("战士b攻击了普通人a,对a造成了10点伤害,a剩余10点生命值.",
                GameLog.getGameLog().buildAttackMessage(
                        a.getName(), a.getJobName(), 10, a.getAttribute().getAttribute(AttributeType.HP), attacker, new BuffPackage()));
    }

    @Test
    public void testBuildSoliderWithWeaponAttackAndLuckyMessage() throws Exception {
        SoliderPlayer attacker = SoliderPlayer.getNewSoliderPlayer("b", 10, 10);
        attacker.setWeapon(new NormalLuckyWeapon(0,  new Random()));
        final Player a = NormalPlayer.getNewNormalPlayer("a", 10, 10);
        BuffPackage buffPackage = new BuffPackage();
        buffPackage.addImmediatelyBuff(new LuckBuff(3));

        assertEquals("战士b用normal lucky攻击了普通人a,b发动了全力一击,对a造成了10点伤害,a剩余10点生命值.",
                GameLog.getGameLog().buildAttackMessage(
                        a.getName(), a.getJobName(), 10, a.getAttribute().getAttribute(AttributeType.HP), attacker, buffPackage));
    }

    @Test
    public void testBuildSoliderWithWeaponAttackAndContinueMessage() throws Exception {
        SoliderPlayer attacker = SoliderPlayer.getNewSoliderPlayer("b", 10, 10);
        attacker.setWeapon(new NormalLuckyWeapon(0,  new Random()));
        final Player a = NormalPlayer.getNewNormalPlayer("a", 10, 10);
        BuffPackage buffPackage = new BuffPackage();
        buffPackage.addContinueBuff(new DizzyBuff(3));

        assertEquals("战士b用normal lucky攻击了普通人a,对a造成了10点伤害,a晕倒了,a剩余10点生命值.",
                GameLog.getGameLog().buildAttackMessage(
                        a.getName(), a.getJobName(), 10, a.getAttribute().getAttribute(AttributeType.HP), attacker, buffPackage));
    }

    @Test
    public void testDizzyMessage() {
        assertEquals("b晕倒了，无法攻击, 眩晕还剩：1轮", GameLog.getGameLog().buildDizzyMessage("b", 1));
    }

    @Test
    public void testContinueBuffHurtMessage() {
        assertEquals("b受到2点毒性伤害, b剩余生命：10",
                GameLog.getGameLog().buildContinueBuffHurtMessage("b", AttributeType.POISONOUS, 2, 10));
    }

    @Test
    public void testBuildWinnerMessage() throws Exception {
        Game game = new DefaultGame(NormalPlayer.getNewNormalPlayer("a", 1, 1), NormalPlayer.getNewNormalPlayer("b", -1, -1));
        assertEquals("a胜利了.", GameLog.getGameLog().buildWinnerMessage(game));
    }
}
package logic.log;

import logic.attribute.AttributeType;
import logic.buff.DizzyBuff;
import logic.buff.LuckBuff;
import logic.buff.NormalAttackBuff;
import logic.buff.buffPackage.BuffFromMessage;
import logic.buff.buffPackage.BuffPackage;
import logic.game.DefaultGame;
import logic.game.Game;
import logic.unit.item.weapon.impl.NormalLuckyWeapon;
import logic.unit.player.NormalPlayer;
import logic.unit.player.Player;
import logic.unit.player.SoliderPlayer;
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

        BuffPackage buffPackage = new BuffPackage();
        BuffFromMessage buffFromMessage = new BuffFromMessage();
        buffFromMessage.addBuffFrom(NormalPlayer.getNewNormalPlayer("b", 1, 1));
        buffPackage.addImmediatelyBuff(buffFromMessage, new NormalAttackBuff(10));

        assertEquals("普通人b攻击了普通人a,对a造成了10点伤害,a剩余10点生命值.",
                GameLog.getGameLog().buildBeAttackMessage(buffPackage, a));
    }

    @Test
    public void testBuildSoliderWithWeaponAttackAndLuckyMessage() throws Exception {
        final Player a = NormalPlayer.getNewNormalPlayer("a", 10, 10);

        BuffPackage buffPackage = new BuffPackage();
        BuffFromMessage buffFromMessage = new BuffFromMessage();
        buffFromMessage.addBuffFrom(SoliderPlayer.getNewSoliderPlayer("b", 1, 1));
        buffFromMessage.addBuffFrom(new NormalLuckyWeapon(1, new Random()));
        buffPackage.addImmediatelyBuff(buffFromMessage, new NormalAttackBuff(10));
        buffPackage.addImmediatelyBuff(buffFromMessage, new LuckBuff(3));

        assertEquals("战士b用normal lucky攻击了普通人a,b发动了全力一击,对a造成了30点伤害,a剩余10点生命值.",
                GameLog.getGameLog().buildBeAttackMessage(buffPackage, a));
    }

    @Test
    public void testBuildSoliderWithWeaponAttackAndContinueMessage() throws Exception {
        final Player a = NormalPlayer.getNewNormalPlayer("a", 10, 10);

        BuffPackage buffPackage = new BuffPackage();
        BuffFromMessage buffFromMessage = new BuffFromMessage();
        buffFromMessage.addBuffFrom(SoliderPlayer.getNewSoliderPlayer("b", 1, 1));
        buffFromMessage.addBuffFrom(new NormalLuckyWeapon(1, new Random()));
        buffPackage.addImmediatelyBuff(buffFromMessage, new NormalAttackBuff(10));
        buffPackage.addContinueBuff(buffFromMessage, new DizzyBuff(3));

        assertEquals("战士b用normal lucky攻击了普通人a,对a造成了10点伤害,a晕倒了,a剩余10点生命值.",
                GameLog.getGameLog().buildBeAttackMessage(buffPackage, a));
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
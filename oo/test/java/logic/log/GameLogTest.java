package logic.log;

import logic.unit.player.NormalPlayer;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xlo on 15/11/29.
 * it's the testing code for game log
 */
public class GameLogTest {

    @Test
    public void testBuildAttackMessage() throws Exception {
        assertEquals("b攻击了a,对a造成了10点伤害,a剩余10点生命值.", GameLog.getGameLog().buildAttackMessage(new NormalPlayer("a", 10, 10, GameLog.getGameLog()),
                new NormalPlayer("b", 10, 10, GameLog.getGameLog())));
    }

    @Test
    public void testBuildWinnerMessage() throws Exception {
        assertEquals("a胜利了.", GameLog.getGameLog().buildWinnerMessage(new NormalPlayer("a", 10, 10, GameLog.getGameLog())));
    }
}
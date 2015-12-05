package main;

import logic.game.DefaultGame;
import logic.game.Game;
import logic.log.GameLog;
import logic.unit.item.Equip;
import logic.unit.item.weapon.BasicLawWeapon;
import logic.unit.item.weapon.impl.LongFireWeapon;
import logic.unit.player.ElderlyPlayer;
import logic.unit.player.PaladinPlayer;

import java.util.Random;

/**
 * Created by xlo on 2015/12/1.
 * 暴力膜蛤不可取
 */
public class PlayerVsUther {

    public static void main(String[] args) throws Exception {
        ElderlyPlayer playerA = ElderlyPlayer.getNewNormalPlayer("我蛤", 10000, 1);
        playerA.setEquip(new Equip("选举法", 10000));
        playerA.setWeapon(new BasicLawWeapon(1, new Random()));

        PaladinPlayer playerB = PaladinPlayer.getNewPaladinPlayer("Uther", 30, 1);
        playerB.setEquip(new Equip("a", 1));
        playerB.setWeapon(new LongFireWeapon(4, new Random()));

        Game game = new DefaultGame(playerA, playerB);
        while (game.getWinner() == null) {
            game.runOneRound();
        }
        GameLog.getGameLog().showWinner(game);
    }

}

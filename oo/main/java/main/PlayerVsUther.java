package main;

import logic.game.DefaultGame;
import logic.game.Game;
import logic.log.GameLog;
import logic.unit.item.Equip;
import logic.unit.item.weapon.impl.LongFireWeapon;
import logic.unit.item.weapon.impl.NormalDizzyWeapon;
import logic.unit.player.NormalPlayer;
import logic.unit.player.PaladinPlayer;

import java.util.Random;

/**
 * Created by xlo on 2015/12/1.
 * it's the main class
 */
public class PlayerVsUther {

    public static void main(String[] args) throws Exception {
        NormalPlayer playerA = new NormalPlayer("我蛤", 10000, 1);
        playerA.setEquip(new Equip("基本法", 10000));
        playerA.setWeapon(new NormalDizzyWeapon(1, new Random()));

        PaladinPlayer playerB = new PaladinPlayer("Uther", 30, 1);
        playerB.setEquip(new Equip("a", 1));
        playerB.setWeapon(new LongFireWeapon(4, new Random()));

        Game game = new DefaultGame(playerA, playerB);
        while (game.getWinner() == null) {
            game.runOneRound();
        }
        GameLog.getGameLog().showWinner(game.getWinner());
    }

}

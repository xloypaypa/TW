package main;

import logic.game.DefaultGame;
import logic.game.Game;
import logic.log.GameLog;
import logic.unit.item.Equip;
import logic.unit.item.weapon.impl.LongColdWeapon;
import logic.unit.item.weapon.impl.ShortLuckyWeapon;
import logic.unit.player.PaladinPlayer;
import logic.unit.player.StalkerPlayer;

import java.util.Random;

/**
 * Created by xlo on 2015/12/1.
 * it's the game
 */
public class ValeeraVSUther {

    public static void main(String[] args) throws Exception {
        StalkerPlayer playerA = new StalkerPlayer("Valeera", 30, 0);
        playerA.setWeapon(new ShortLuckyWeapon(3, new Random()));

        PaladinPlayer playerB = new PaladinPlayer("Uther", 30, 1);
        playerB.setEquip(new Equip("a", 1));
        playerB.setWeapon(new LongColdWeapon(4, new Random()));

        Game game = new DefaultGame(playerA, playerB);
        while (game.getWinner() == null) {
            game.runOneRound();
        }
        GameLog.getGameLog().showWinner(game);
    }
}

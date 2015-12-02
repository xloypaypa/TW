package main;

import logic.game.DefaultGame;
import logic.game.Game;
import logic.log.GameLog;
import logic.unit.item.Equip;
import logic.unit.item.weapon.impl.LongFireWeapon;
import logic.unit.item.weapon.impl.NormalLuckyWeapon;
import logic.unit.player.PaladinPlayer;
import logic.unit.player.SoliderPlayer;

import java.util.Random;

/**
 * Created by xlo on 2015/12/1.
 * it's the main class
 */
public class UtherVSGarrosh {

    public static void main(String[] args) throws Exception {
        PaladinPlayer playerA = new PaladinPlayer("Uther", 30, 1);
        playerA.setEquip(new Equip("a", 1));
        playerA.setWeapon(new LongFireWeapon(4, new Random()));

        SoliderPlayer playerB = new SoliderPlayer("Garrosh", 30, 0);
        playerB.setEquip(new Equip("a", 2));
        playerB.setWeapon(new NormalLuckyWeapon(3, new Random()));

        Game game = new DefaultGame(playerA, playerB);
        while (game.getWinner() == null) {
            game.runOneRound();
        }
        GameLog.getGameLog().showWinner(game);
    }

}

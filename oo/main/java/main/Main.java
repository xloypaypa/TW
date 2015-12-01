package main;

import logic.game.DefaultGame;
import logic.game.Game;
import logic.log.GameLog;
import logic.unit.item.Equip;
import logic.unit.item.weapon.impl.NormalDizzyWeapon;
import logic.unit.item.weapon.impl.NormalLuckyWeapon;
import logic.unit.player.SoliderPlayer;

import java.util.Random;

/**
 * Created by xlo on 15/11/28.
 * it's the main class
 */
public class Main {

    public static void main(String[] args) throws Exception {
        SoliderPlayer playerA = new SoliderPlayer("a", 100, 3);
        playerA.setEquip(new Equip("a", 2));
        playerA.setWeapon(new NormalLuckyWeapon(3, new Random()));

        SoliderPlayer playerB = new SoliderPlayer("b", 100, 3);
        playerB.setEquip(new Equip("a", 1));
        playerB.setWeapon(new NormalDizzyWeapon(1, new Random()));

        Game game = new DefaultGame(playerA,
                playerB);
        while (game.getWinner() == null) {
            game.runOneRound();
        }
        GameLog.getGameLog().showWinner(game.getWinner());
    }
}

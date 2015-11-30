package main;

import logic.game.DefaultGame;
import logic.game.Game;
import logic.log.GameLog;
import logic.unit.item.Equip;
import logic.unit.item.weapon.impl.NormalWeaponImpl;
import logic.unit.player.NormalPlayer;
import logic.unit.player.SoliderPlayer;

import java.util.Random;

/**
 * Created by xlo on 15/11/28.
 * it's the main class
 */
public class Main {

    public static void main(String[] args) throws Exception {
        SoliderPlayer soliderPlayer = new SoliderPlayer("b", 100, 20, GameLog.getGameLog());
        soliderPlayer.setEquip(new Equip("a", 1));
        soliderPlayer.setWeapon(new NormalWeaponImpl(2, new Random()));
        Game game = new DefaultGame(new NormalPlayer("a", 100, 10),
                soliderPlayer);
        while (game.getWinner() == null) {
            game.runOneRound();
        }
        GameLog.getGameLog().showWinner(game.getWinner());
    }
}

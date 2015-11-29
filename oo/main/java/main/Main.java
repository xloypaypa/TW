package main;

import logic.Game;
import logic.Normal;

/**
 * Created by xlo on 15/11/28.
 * it's the main class
 */
public class Main {

    public static void main(String[] args) {
        Game game = new Game(new Normal("李四", 100, 10), new Normal("haha", 1000, 10));
        while (!game.isEnd()) {
            String now = game.nextRound();
            System.out.println(now);
        }
    }
}

package main;

import logic.Game;
import logic.Human;

/**
 * Created by xlo on 15/11/28.
 * it's the main class
 */
public class Main {

    public static void main(String[] args) {
        Game game = new Game(new Human("李四", 1, 1), new Human("haha", 1000, 100));
        while (true) {
            String now = game.nextRound();
            if (now!=null) {
                System.out.println(now);
                break;
            }
        }
    }
}

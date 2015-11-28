package logic;

import java.text.DecimalFormat;

/**
 * Created by xlo on 15/11/28.
 * it's the game
 */
public class Game {

    private int round;
    private Human playerA, playerB;

    public Game(Human playerA, Human playerB) {
        this.round = 0;
        this.playerA = playerA;
        this.playerB = playerB;
    }

    public String nextRound() {
        this.round++;
        if (this.round % 2 == 0) {
            return nowRound(playerA, playerB);
        } else {
            return nowRound(playerB, playerA);
        }
    }

    private String nowRound(Human from, Human to) {
        from.decHp(to.getAttack());
        DecimalFormat decimalFormat = new DecimalFormat("0");
        System.out.printf("%s攻击了%s,%s受到了%s点伤害,%s剩余%s%n"
                , to.getName(), from.getName(), from.getName(),
                decimalFormat.format(to.getAttack()), from.getName(),
                decimalFormat.format(from.getHp()));
        if (from.isDie()) {
            return from.getName() + "被打败了";
        }
        return null;
    }

}

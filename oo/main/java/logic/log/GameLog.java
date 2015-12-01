package logic.log;

import logic.attribute.AttributeType;
import logic.buff.BuffPackage;
import logic.buff.ContinueBuff;
import logic.job.Job;
import logic.unit.player.Player;
import logic.unit.player.SoliderPlayer;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by xlo on 15/11/29.
 * it's the game log
 */
public class GameLog {

    private static GameLog gameLog;

    private GameLog() {

    }

    public static GameLog getGameLog() {
        if (gameLog == null) {
            synchronized (GameLog.class) {
                if (gameLog == null) {
                    gameLog = new GameLog();
                }
            }
        }
        return gameLog;
    }

    public void afterPlayerBeAttacked(String playerName, String playerJobName, float hurt, float playerHpRemain, Player attacker, BuffPackage buffPackage) {
        System.out.println(buildAttackMessage(playerName, playerJobName, hurt, playerHpRemain, attacker, buffPackage));
    }

    public void showWinner(Player winner) {
        System.out.println(buildWinnerMessage(winner));
    }

    public void showContinueBuffHurt(String playerName, AttributeType type, float value, float playerHpRemain) {
        System.out.println(buildContinueBuffHurtMessage(playerName, type, value, playerHpRemain));
    }

    public void showDizzy(String playerName, int round) {
        System.out.println(buildDizzyMessage(playerName, round));
    }

    String buildContinueBuffHurtMessage(String playerName, AttributeType type, float value, float playerHpRemain) {
        String message = "";
        DecimalFormat decimalFormat = new DecimalFormat("0");
        message += playerName + "受到" + decimalFormat.format(value) + "点";
        if (type.equals(AttributeType.POISONOUS)) {
            message += "毒性";
        } else if (type.equals(AttributeType.FIRE)) {
            message += "火焰";
        }
        message += "伤害, " + playerName + "剩余生命：" + decimalFormat.format(playerHpRemain);
        return message;
    }

    String buildDizzyMessage(String playerName, int round) {
        String message = "";
        message += playerName + "晕倒了，无法攻击, 眩晕还剩：" + round + "轮";
        return message;
    }

    String buildWinnerMessage(Player winner) {
        return winner.getName() + "胜利了.";
    }

    String buildAttackMessage(String playerName, String playerJobName, float hurt, float playerHpRemain, Player attacker, BuffPackage buffPackage) {
        boolean isLucky = buffPackage.getImmediatelyEffect().getAttribute(AttributeType.LUCK) > 0;
        DecimalFormat decimalFormat = new DecimalFormat("0");
        String message = "";

        String attackerJobName = attacker.getJobName();
        String attackerTitle = attackerJobName + attacker.getName();

        message += attackerTitle;
        if (attackerJobName.equals(Job.solider) && attacker.getClass().equals(SoliderPlayer.class) &&
                attacker.getWeapon() != null) {
            message += "用" + attacker.getWeapon().getName();
        }
        message += "攻击了" + playerJobName + playerName;
        if (isLucky) {
            message += "," + attacker.getName() + "发动了全力一击";
        }
        message += ",对"
                + playerName + "造成了" + decimalFormat.format(hurt) + "点伤害,";
        List<ContinueBuff> continueBuffs = buffPackage.getContinueBuffs();
        for (ContinueBuff now : continueBuffs) {
            if (now.getEffect().getAttribute(AttributeType.FIRE) > 0) {
                message += playerName + "热到了,";
            } else if (now.getEffect().getAttribute(AttributeType.COLD) > 0) {
                message += playerName + "冷到了,";
            } else if (now.getEffect().getAttribute(AttributeType.POISONOUS) > 0) {
                message += playerName + "中毒了,";
            } else if (now.getEffect().getAttribute(AttributeType.DIZZY) > 0) {
                message += playerName + "晕倒了,";
            }
        }
        message += playerName + "剩余"
                + decimalFormat.format(playerHpRemain) + "点生命值.";
        return message;
    }

}

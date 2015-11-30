package logic.log;

import logic.attribute.AttributeType;
import logic.job.Job;
import logic.unit.player.Player;
import logic.unit.player.SoliderPlayer;

import java.text.DecimalFormat;

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

    public void afterPlayerBeAttacked(String playerName, String playerJobName, float hurt, float playerHpRemain, Player attacker, boolean isLucky) {
        System.out.println(buildAttackMessage(playerName, playerJobName, hurt, playerHpRemain, attacker, isLucky));
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

    protected String buildContinueBuffHurtMessage(String playerName, AttributeType type, float value, float playerHpRemain) {
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

    protected String buildDizzyMessage(String playerName, int round) {
        String message = "";
        message += playerName + "晕倒了，无法攻击, 眩晕还剩：" + round + "轮";
        return message;
    }

    protected String buildWinnerMessage(Player winner) {
        return winner.getName() + "胜利了.";
    }

    protected String buildAttackMessage(String playerName, String playerJobName, float hurt, float playerHpRemain, Player attacker, boolean isLucky) {
        DecimalFormat decimalFormat = new DecimalFormat("0");
        String message = "";

        String attackerJobName = attacker.getJobName();
        String attackerTitle = attackerJobName + attacker.getName();

        message += attackerTitle;
        if (attackerJobName.equals(Job.solider) && attacker.getClass().equals(SoliderPlayer.class) &&
                ((SoliderPlayer) attacker).getWeapon() != null) {
            message += "用" + ((SoliderPlayer) attacker).getWeapon().getName();
        }
        message += "攻击了" + playerJobName + playerName;
        if (isLucky) {
            message += "," + attacker.getName() + "发动了全力一击";
        }
        message += ",对"
                + playerName + "造成了" + decimalFormat.format(hurt) + "点伤害,"
                + playerName + "剩余"
                + decimalFormat.format(playerHpRemain) + "点生命值.";
        return message;
    }

}

package logic.log;

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

    public void afterPlayerBeAttacked(Player player, Player attacker) {
        System.out.println(buildAttackMessage(player, attacker));
    }

    protected String buildAttackMessage(Player player, Player attacker) {
        DecimalFormat decimalFormat = new DecimalFormat("0");
        String message = "";
        message += attacker.getJobName() + attacker.getName();
        if (attacker.getJobName().equals(Job.solider) && attacker.getClass().equals(SoliderPlayer.class) &&
                ((SoliderPlayer) attacker).getWeapon()!=null) {
            message += "用" + ((SoliderPlayer) attacker).getWeapon().getName();
        }
        message += "攻击了" + player.getJobName() + player.getName() + ",对" +
                player.getName() + "造成了" + decimalFormat.format(player.calculateHurt(attacker)) + "点伤害,"
                + player.getName() + "剩余" + decimalFormat.format(player.getHp()) + "点生命值.";
        return message;
    }

    public void showWinner(Player winner) {
        System.out.println(buildWinnerMessage(winner));
    }

    protected String buildWinnerMessage(Player winner) {
        return winner.getName() + "胜利了.";
    }

}

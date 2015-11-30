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

    public void afterPlayerBeAttacked(String playerName, String playerJobName, float hurt, float playerHpRemain, Player attacker) {
        System.out.println(buildAttackMessage(playerName, playerJobName, hurt, playerHpRemain, attacker));
    }

    protected String buildAttackMessage(String playerName, String playerJobName, float hurt, float playerHpRemain, Player attacker) {
        DecimalFormat decimalFormat = new DecimalFormat("0");
        String message = "";

        String attackerJobName = attacker.getJobName();
        String attackerTitle = attackerJobName + attacker.getName();

        message += attackerTitle;
        if (attackerJobName.equals(Job.solider) && attacker.getClass().equals(SoliderPlayer.class) &&
                ((SoliderPlayer) attacker).getWeapon()!=null) {
            message += "用" + ((SoliderPlayer) attacker).getWeapon().getName();
        }
        message += "攻击了" + playerJobName + playerName + ",对"
                + playerName + "造成了" + decimalFormat.format(hurt) + "点伤害,"
                + playerName + "剩余"
                + decimalFormat.format(playerHpRemain) + "点生命值.";
        return message;
    }

    public void showWinner(Player winner) {
        System.out.println(buildWinnerMessage(winner));
    }

    protected String buildWinnerMessage(Player winner) {
        return winner.getName() + "胜利了.";
    }

}

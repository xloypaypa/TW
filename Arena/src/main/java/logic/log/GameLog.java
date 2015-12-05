package logic.log;

import logic.attribute.AttributeType;
import logic.buff.ContinueBuff;
import logic.buff.buffPackage.BuffFromMessage;
import logic.buff.buffPackage.BuffPackage;
import logic.game.Game;
import logic.unit.item.weapon.Weapon;
import logic.unit.player.ElderlyPlayer;
import logic.unit.player.Player;

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

    public void playerBeAttacker(BuffPackage buffPackage, Player player) {
        String s = buildBeAttackMessage(buffPackage, player);
        if (s.length() != 0) {
            System.out.println(s);
        }
    }

    String buildBeAttackMessage(BuffPackage buffPackage, Player player) {
        boolean isLucky = buffPackage.getImmediatelyEffect().getAttribute(AttributeType.LUCK) > 0;
        DecimalFormat decimalFormat = new DecimalFormat("0");
        String message = "";

        Player attacker = null;
        Weapon weapon = null;
        for (BuffFromMessage.BuffFromMessageNode node : buffPackage.getBuffFromMessage().getBuffFromMessageNodes()) {
            if (node.getObject() instanceof Player) {
                attacker = (Player) node.getObject();
            } else if (node.getObject() instanceof Weapon) {
                weapon = (Weapon) node.getObject();
            }
        }

        if (attacker == null) {
            return "";
        }

        message += attacker.getJobName() + attacker.getName();
        if (weapon != null) {
            message += "用" + weapon.getName();
        }
        message += "攻击了" + player.getJobName() + player.getName();
        if (isLucky) {
            message += "," + attacker.getName() + "发动了全力一击";
        }

        message += ",对"
                + player.getName() + "造成了"
                + decimalFormat.format(-buffPackage.getImmediatelyEffect().getAttribute(AttributeType.HP)) + "点伤害,";
        List<ContinueBuff> continueBuffs = buffPackage.getContinueBuffs();
        for (ContinueBuff now : continueBuffs) {
            if (now.getEffect().getAttribute(AttributeType.FIRE) > 0) {
                message += player.getName() + "热到了,";
            } else if (now.getEffect().getAttribute(AttributeType.COLD) > 0) {
                message += player.getName() + "冷到了,";
            } else if (now.getEffect().getAttribute(AttributeType.POISONOUS) > 0) {
                message += player.getName() + "中毒了,";
            } else if (now.getEffect().getAttribute(AttributeType.DIZZY) > 0) {
                message += player.getName() + "晕倒了,";
            }
        }
        message += player.getName() + "剩余"
                + decimalFormat.format(player.getAttribute().getAttribute(AttributeType.HP)) + "点生命值.";
        return message;
    }

    public void showWinner(Game game) {
        System.out.println(buildWinnerMessage(game));
    }

    String buildWinnerMessage(Game game) {
        try {
            Player winner = game.getWinner();
            if (winner instanceof ElderlyPlayer) {
                return "不要总想搞个大新闻.";
            }
            return winner.getName() + "胜利了.";
        } catch (Exception e) {
            return "平局.";
        }
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
        if (type.equals(AttributeType.POISONOUS)) {
            message += playerName + "受到" + decimalFormat.format(value) + "点毒性伤害, ";
        } else if (type.equals(AttributeType.FIRE)) {
            message += playerName + "受到" + decimalFormat.format(value) + "点火焰伤害, ";
        } else if (type.equals(AttributeType.LIFE_EXPERIENCE)) {
            message += playerName + "获得了" + decimalFormat.format(value) + "点人生的经验, ";
        } else if (type.equals(AttributeType.CONTINUE_ONE_SECOND)) {
            message += "续一秒, ";
        }
        message += playerName + "剩余生命：" + decimalFormat.format(playerHpRemain);
        return message;
    }

    String buildDizzyMessage(String playerName, int round) {
        String message = "";
        message += playerName + "晕倒了，无法攻击, 眩晕还剩：" + round + "轮";
        return message;
    }

}

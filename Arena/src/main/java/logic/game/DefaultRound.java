package logic.game;

import logic.attribute.AttributeType;
import logic.buff.buffPackage.BuffPackage;
import logic.log.GameLog;
import logic.unit.player.Player;

/**
 * Created by xlo on 15/11/30.
 * it's the default round
 */
public class DefaultRound extends Round {

    final float[] playerOldHp;
    final boolean[] attacked;
    private final GameLog gameLog;
    BuffPackage buffPackage;

    public DefaultRound(Player[] players, GameLog gameLog) {
        super(players);
        this.playerOldHp = new float[players.length];
        this.attacked = new boolean[players.length];
        this.gameLog = gameLog;
    }

    @Override
    protected RoundStatus whenRoundStart() {
        for (Player now : players) {
            now.getPlayerBuff().calculateUserContinueBuff();
        }
        return RoundStatus.ACTION_START;
    }

    @Override
    protected RoundStatus whenActionStart() {
        saveUserHp();

        for (int i = 0; i < players.length; i++) {
            if (!attacked[i]) {
                attacked[i] = true;
                attacker = players[i];
                RoundStatus roundStatus =  checkAttackerIsOk(attacker);
                addEquipEffect();
                return roundStatus;
            }
        }
        return RoundStatus.ROUND_END;
    }

    @Override
    protected RoundStatus whenAction() {
        buffPackage = attacker.getAttack();
        for (Player now : players) {
            if (now == attacker) {
                continue;
            }
            now.attachBuff(buffPackage);
        }
        return RoundStatus.ACTION_END;
    }

    @Override
    protected RoundStatus whenActionEnd() {
        for (Player now : players) {
            now.getPlayerBuff().calculateUserImmediatelyBuff();
        }
        if (buffPackage == null) {
            return RoundStatus.ACTION_START;
        }
        for (int i = 0; i < players.length; i++) {
            Player now = players[i];
            if (now == attacker && now.getAttribute().getAttribute(AttributeType.HP) == playerOldHp[i]) {
                continue;
            }

            gameLog.afterPlayerBeAttacked(now.getName(), now.getJobName(),
                    playerOldHp[i] - now.getAttribute().getAttribute(AttributeType.HP),
                    now.getAttribute().getAttribute(AttributeType.HP), attacker, buffPackage);
        }
        return checkDieEnd();
    }

    @Override
    protected RoundStatus whenRoundEnd() {
        return null;
    }

    private RoundStatus checkAttackerIsOk(Player attacker) {
        return attacker.getPlayerBuff().checkUserCanAttack() ? RoundStatus.ACTION : RoundStatus.ACTION_END;
    }

    private void saveUserHp() {
        for (int i = 0; i < playerOldHp.length; i++) {
            playerOldHp[i] = players[i].getAttribute().getAttribute(AttributeType.HP);
        }
    }

    private void addEquipEffect() {
        for (Player now : players) {
            if (now.getEquip() != null) {
                now.attachBuff(now.getEquip().getDefence());
            }
        }
    }

    private RoundStatus checkDieEnd() {
        for (Player player : players) {
            if (!player.isAlive()) {
                return RoundStatus.ROUND_END;
            }
        }
        return RoundStatus.ACTION_START;
    }
}

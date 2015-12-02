package logic.game;

import logic.attribute.Attribute;
import logic.attribute.AttributeType;
import logic.buff.BuffPackage;
import logic.buff.ColdBuff;
import logic.buff.ContinueBuff;
import logic.log.GameLog;
import logic.unit.player.Player;

import java.util.List;

/**
 * Created by xlo on 15/11/30.
 * it's the default round
 */
public class DefaultRound extends Round {

    final AttributeType[] buffsCalculateInRoundStart = {AttributeType.FIRE, AttributeType.POISONOUS,
            AttributeType.LIFE_EXPERIENCE, AttributeType.CONTINUE_ONE_SECOND};

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
        calculateUserContinueBuff();
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
            now.immediatelyBuffToAttribute();
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
        RoundStatus roundStatus = RoundStatus.ACTION;
        List<ContinueBuff> continueBuffs = attacker.getBuff().getContinueBuffsWith(AttributeType.DIZZY);
        for (ContinueBuff continueBuff : continueBuffs) {
            gameLog.showDizzy(this.attacker.getName(), continueBuff.remainRound());
            continueBuff.effected();
            roundStatus = RoundStatus.ACTION_END;
        }

        continueBuffs = this.attacker.getBuff().getContinueBuffsWith(AttributeType.COLD);
        for (ContinueBuff continueBuff : continueBuffs) {
            assert continueBuff instanceof ColdBuff;
            if (((ColdBuff) continueBuff).isDizzy()) {
                roundStatus = RoundStatus.ACTION_END;
            }
        }
        continueBuffs.forEach(ContinueBuff::effected);
        this.attacker.getBuff().clearContinueBuff();
        return roundStatus;
    }

    private void calculateContinueHurt(Player now, AttributeType attributeType) {
        List<ContinueBuff> continueBuffs;
        Attribute attribute = new Attribute();
        float sum = 0;
        continueBuffs = now.getBuff().getContinueBuffsWith(attributeType);
        for (ContinueBuff continueBuff : continueBuffs) {
            sum += continueBuff.getEffect().getAttribute(attributeType);
        }
        if (sum != 0) {
            attribute.setAttribute(AttributeType.HP, -sum);
            now.getAttribute().mergeAttribute(attribute);
            gameLog.showContinueBuffHurt(now.getName(), attributeType, sum, now.getAttribute().getAttribute(AttributeType.HP));
        }
    }

    private void saveUserHp() {
        for (int i = 0; i < playerOldHp.length; i++) {
            playerOldHp[i] = players[i].getAttribute().getAttribute(AttributeType.HP);
        }
    }

    private void calculateUserContinueBuff() {
        for (Player now : players) {
            for (AttributeType attributeType : buffsCalculateInRoundStart) {
                calculateContinueHurt(now, attributeType);
            }
            now.getBuff().clearContinueBuff();
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

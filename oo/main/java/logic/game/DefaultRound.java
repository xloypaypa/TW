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

    final float[] defenderOldHp;
    private final GameLog gameLog;
    BuffPackage buffPackage;

    public DefaultRound(Player attacker, Player[] defender, GameLog gameLog) {
        super(attacker, defender);
        this.defenderOldHp = new float[defender.length];
        this.gameLog = gameLog;
    }

    @Override
    protected RoundStatus whenRoundStart() {
        RoundStatus roundStatus = RoundStatus.ACTION_START;
        List<ContinueBuff> continueBuffs = attacker.getBuff().getContinueBuffsWith(AttributeType.DIZZY);
        for (ContinueBuff continueBuff : continueBuffs) {
            gameLog.showDizzy(attacker.getName(), continueBuff.remainRound());
            continueBuff.effected();
            roundStatus = RoundStatus.ROUND_END;
        }

        continueBuffs = attacker.getBuff().getContinueBuffsWith(AttributeType.COLD);
        for (ContinueBuff continueBuff : continueBuffs) {
            assert continueBuff instanceof ColdBuff;
            if (((ColdBuff) continueBuff).isDizzy()) {
                roundStatus = RoundStatus.ROUND_END;
            }
        }
        continueBuffs.forEach(ContinueBuff::effected);
        attacker.getBuff().clearContinueBuff();

        for (Player now : defender) {
            for (AttributeType attributeType : buffsCalculateInRoundStart) {
                calculateContinueHurt(now, attributeType);
            }
            now.getBuff().clearContinueBuff();
        }
        return roundStatus;
    }

    @Override
    protected RoundStatus whenActionStart() {
        for (Player now : defender) {
            if (now.getEquip() != null) {
                now.attachBuff(now.getEquip().getDefence());
            }
        }
        for (int i = 0; i < defenderOldHp.length; i++) {
            defenderOldHp[i] = defender[i].getAttribute().getAttribute(AttributeType.HP);
        }
        return RoundStatus.ACTION;
    }

    @Override
    protected RoundStatus whenAction() {
        buffPackage = attacker.getAttack();
        for (Player now : defender) {
            now.attachBuff(buffPackage);
        }
        return RoundStatus.ACTION_END;
    }

    @Override
    protected RoundStatus whenActionEnd() {
        for (int i = 0; i < defender.length; i++) {
            Player now = defender[i];
            now.immediatelyBuffToAttribute();

            gameLog.afterPlayerBeAttacked(now.getName(), now.getJobName(),
                    defenderOldHp[i] - now.getAttribute().getAttribute(AttributeType.HP),
                    now.getAttribute().getAttribute(AttributeType.HP), attacker, buffPackage);
        }
        return RoundStatus.ROUND_END;
    }

    @Override
    protected RoundStatus whenRoundEnd() {
        return null;
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
}

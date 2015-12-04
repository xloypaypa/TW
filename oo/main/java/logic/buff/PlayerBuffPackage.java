package logic.buff;

import logic.attribute.Attribute;
import logic.attribute.AttributeType;
import logic.log.GameLog;
import logic.unit.player.Player;

import java.util.List;

/**
 * Created by xlo on 2015/12/4.
 * it's the player buff package
 */
public class PlayerBuffPackage extends BuffPackage {

    final AttributeType[] buffsCalculateInRoundStart = {AttributeType.FIRE, AttributeType.POISONOUS,
            AttributeType.LIFE_EXPERIENCE, AttributeType.CONTINUE_ONE_SECOND};

    private Player player;
    private GameLog gameLog;

    public PlayerBuffPackage(Player player, GameLog gameLog) {
        this.player = player;
        this.gameLog = gameLog;
    }

    public void calculateUserImmediatelyBuff() {
        player.getAttribute().mergeAttribute(this.getImmediatelyEffect());
        this.clearImmediatelyBuff();
    }

    public void calculateUserContinueBuff() {
        for (AttributeType attributeType : buffsCalculateInRoundStart) {
            calculateContinueHurt(player, attributeType);
        }
        this.clearContinueBuff();
    }

    public boolean checkUserCanAttack() {
        boolean canAttack = true;
        List<ContinueBuff> continueBuffs = this.getContinueBuffsWith(AttributeType.DIZZY);
        for (ContinueBuff continueBuff : continueBuffs) {
            gameLog.showDizzy(this.player.getName(), continueBuff.remainRound());
            continueBuff.effected();
            canAttack = false;
        }

        continueBuffs = this.getContinueBuffsWith(AttributeType.COLD);
        for (ContinueBuff continueBuff : continueBuffs) {
            assert continueBuff instanceof ColdBuff;
            if (((ColdBuff) continueBuff).isDizzy()) {
                canAttack = false;
            }
        }
        continueBuffs.forEach(ContinueBuff::effected);
        this.clearContinueBuff();
        return canAttack;
    }

    public Player getPlayer() {
        return player;
    }

    private void calculateContinueHurt(Player player, AttributeType attributeType) {
        List<ContinueBuff> continueBuffs;
        Attribute attribute = new Attribute();
        float sum = 0;
        continueBuffs = this.getContinueBuffsWith(attributeType);
        for (ContinueBuff continueBuff : continueBuffs) {
            sum += continueBuff.getEffect().getAttribute(attributeType);
        }
        if (sum != 0) {
            attribute.setAttribute(AttributeType.HP, -sum);
            player.getAttribute().mergeAttribute(attribute);
            gameLog.showContinueBuffHurt(player.getName(), attributeType, sum, player.getAttribute().getAttribute(AttributeType.HP));
        }
    }

}

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

    public void immediatelyBuffToAttribute() {
        player.getAttribute().mergeAttribute(player.getPlayerBuff().getImmediatelyEffect());
        player.getPlayerBuff().clearImmediatelyBuff();
    }

    public void calculateUserContinueBuff() {
        for (AttributeType attributeType : buffsCalculateInRoundStart) {
            calculateContinueHurt(player, attributeType);
        }
        player.getPlayerBuff().clearContinueBuff();
    }

    public Player getPlayer() {
        return player;
    }

    private void calculateContinueHurt(Player now, AttributeType attributeType) {
        List<ContinueBuff> continueBuffs;
        Attribute attribute = new Attribute();
        float sum = 0;
        continueBuffs = now.getPlayerBuff().getContinueBuffsWith(attributeType);
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

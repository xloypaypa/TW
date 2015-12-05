package logic.buff.buffPackage;

import logic.attribute.Attribute;
import logic.attribute.AttributeType;
import logic.buff.ContinueBuff;
import logic.buff.ImmediatelyBuff;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xlo on 15/11/29.
 * it's the buff pack
 */
public class BuffPackage {

    protected BuffFromMessage buffFromMessage;

    private Attribute immediatelyAttribute;

    private final List<ContinueBuff> continueBuffs;

    public BuffPackage() {
        this.immediatelyAttribute = new Attribute();
        this.continueBuffs = new LinkedList<>();
        this.buffFromMessage = new BuffFromMessage();
    }

    public void addImmediatelyBuff(BuffFromMessage buffFromMessage, ImmediatelyBuff buff) {
        this.buffFromMessage.addBuffFromMessage(buffFromMessage);
        immediatelyAttribute.mergeAttribute(buff.getEffect());
    }

    public void addContinueBuff(BuffFromMessage buffFromMessage, ContinueBuff buff) {
        this.buffFromMessage.addBuffFromMessage(buffFromMessage);
        for (ContinueBuff continueBuff : continueBuffs) {
            if (continueBuff.getClass().equals(buff.getClass())) {
                continueBuff.addRound(buff.remainRound());
                return;
            }
        }
        this.continueBuffs.add(buff);
    }

    public List<ContinueBuff> getContinueBuffs() {
        return continueBuffs;
    }

    public List<ContinueBuff> getContinueBuffsWith(AttributeType attributeType) {
        return continueBuffs.stream().filter(continueBuff -> continueBuff.getEffect().getAttribute(attributeType) != 0)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public void addBuffPackage(BuffPackage buffPackage) {
        for (ContinueBuff continueBuff : buffPackage.continueBuffs) {
            this.addContinueBuff(buffPackage.buffFromMessage, continueBuff);
        }
        ImmediatelyBuff immediatelyBuff = () -> buffPackage.immediatelyAttribute;
        addImmediatelyBuff(buffPackage.buffFromMessage, immediatelyBuff);
    }

    public void clearImmediatelyBuff() {
        immediatelyAttribute = new Attribute();
    }

    public void clearContinueBuff() {
        Iterator<ContinueBuff> iterator = continueBuffs.iterator();
        while (iterator.hasNext()) {
            ContinueBuff continueBuff = iterator.next();
            if (continueBuff.remainRound() <= 0) {
                iterator.remove();
            }
        }
    }

    public Attribute getContinueEffect() {
        Attribute attribute = new Attribute();
        for (ContinueBuff continueBuff : continueBuffs) {
            attribute.mergeAttribute(continueBuff.getEffect());
        }
        return attribute;
    }

    public void effectAllContinueBuff() {
        continueBuffs.forEach(ContinueBuff::effected);
    }

    public BuffFromMessage getBuffFromMessage() {
        return buffFromMessage;
    }

    public void clearBuffFromMessage() {
        buffFromMessage.clear();
    }

    public Attribute getImmediatelyEffect() {
        Attribute attribute = new Attribute();
        attribute.mergeAttribute(this.immediatelyAttribute);
        float hurt = -attribute.getAttribute(AttributeType.HP);
        if (hurt > 0) {
            hurt = Math.max(0, hurt - attribute.getAttribute(AttributeType.SAVE_HP));
        }

        if (attribute.getAttribute(AttributeType.LUCK) > 0) {
            hurt *= 3;
        }

        attribute.setAttribute(AttributeType.HP, -hurt);
        return attribute;
    }
}

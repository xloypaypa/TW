package logic.buff;

import logic.attribute.Attribute;
import logic.attribute.AttributeType;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xlo on 15/11/29.
 * it's the buff pack
 */
public class BuffPackage {

    protected Attribute immediatelyAttribute;

    protected List<ContinueBuff> continueBuffs;

    public BuffPackage() {
        this.immediatelyAttribute = new Attribute();
        this.continueBuffs = new LinkedList<>();
    }

    public void addImmediatelyBuff(ImmediatelyBuff buff) {
        immediatelyAttribute.mergeAttribute(buff.getEffect());
    }

    public void addContinueBuff(ContinueBuff buff) {
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
        buffPackage.continueBuffs.forEach(this::addContinueBuff);
        ImmediatelyBuff immediatelyBuff = () -> buffPackage.immediatelyAttribute;
        addImmediatelyBuff(immediatelyBuff);
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

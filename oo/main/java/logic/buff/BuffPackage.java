package logic.buff;

import logic.attribute.Attribute;
import logic.attribute.AttributeType;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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

    public void addBuffPackage(BuffPackage buffPackage) {
        ImmediatelyBuff immediatelyBuff =  () -> buffPackage.immediatelyAttribute;
        buffPackage.continueBuffs.forEach(this::addContinueBuff);
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

    public Attribute getImmediatelyEffect() {
        Attribute attribute = new Attribute();
        attribute.mergeAttribute(this.immediatelyAttribute);
        float hurt = -attribute.getAttribute(AttributeType.HP);
        if (hurt > 0) {
            hurt = Math.max(0, hurt - attribute.getAttribute(AttributeType.SAVE_HP));
        }
        attribute.setAttribute(AttributeType.HP, -hurt);
        return attribute;
    }
}

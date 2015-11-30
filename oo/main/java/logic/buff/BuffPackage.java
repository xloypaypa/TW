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
public class BuffPackage implements Buff {

    protected static final String NOT_CONTINUE_ERROR = "the buff not the continue buff";
    protected Attribute immediatelyAttribute, foreverAttribute;

    protected List<ContinueBuff> continueBuffs;

    public BuffPackage() {
        this.immediatelyAttribute = new Attribute();
        this.foreverAttribute = new Attribute();
        this.continueBuffs = new LinkedList<>();
    }

    public void addImmediatelyBuff(Buff buff) {
        immediatelyAttribute.mergeAttribute(buff.getEffect());
    }

    public void addForeverAttribute(Buff buff) {
        foreverAttribute.mergeAttribute(buff.getEffect());
    }

    public void addContinueAttribute(Buff buff) throws Exception {
        if (buff instanceof ContinueBuff) {
            for (ContinueBuff continueBuff : continueBuffs) {
                if (continueBuff.getClass().equals(buff.getClass())) {
                    continueBuff.addRound(((ContinueBuff) buff).remainRound());
                    return;
                }
            }
            this.continueBuffs.add((ContinueBuff) buff);
        } else if (buff instanceof BuffPackage) {
            for (ContinueBuff continueBuff : ((BuffPackage) buff).continueBuffs) {
                addContinueAttribute(continueBuff);
            }
        } else {
            throw new Exception(NOT_CONTINUE_ERROR);
        }
    }

    public List<ContinueBuff> getContinueBuffs() {
        return continueBuffs;
    }

    public void clearImmediatelyBuff() {
        immediatelyAttribute = new Attribute();
    }

    public void clearForeverBuff() {
        foreverAttribute = new Attribute();
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

    @Override
    public Attribute getEffect() {
        Attribute attribute = new Attribute();
        attribute.mergeAttribute(this.immediatelyAttribute);
        attribute.mergeAttribute(this.foreverAttribute);
        float hurt = -attribute.getAttribute(AttributeType.HP);
        if (hurt > 0) {
            hurt = Math.max(0, hurt - attribute.getAttribute(AttributeType.SAVE_HP));
        }
        attribute.setAttribute(AttributeType.HP, -hurt);
        return attribute;
    }
}

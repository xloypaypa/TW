package logic.buff;

import logic.attribute.Attribute;
import logic.attribute.AttributeType;

/**
 * Created by xlo on 15/11/29.
 * it's the buff pack
 */
public class BuffPackage implements Buff {

    protected Attribute immediatelyAttribute, foreverAttribute, continueAttribute;

    public BuffPackage() {
        this.immediatelyAttribute = new Attribute();
        this.foreverAttribute = new Attribute();
        this.continueAttribute = new Attribute();
    }

    public void addImmediatelyBuff(Buff buff) {
        immediatelyAttribute.mergeAttribute(buff.getEffect());
    }

    public void addForeverAttribute(Buff buff) {
        foreverAttribute.mergeAttribute(buff.getEffect());
    }

    public void addContinueAttribute(Buff buff) {
        continueAttribute.mergeAttribute(buff.getEffect());
    }

    public void clearImmediatelyBuff() {
        immediatelyAttribute = new Attribute();
    }

    public void clearForeverBuff() {
        foreverAttribute = new Attribute();
    }

    public void clearContinueBuff() {
        continueAttribute = new Attribute();
    }

    @Override
    public Attribute getEffect() {
        Attribute attribute = new Attribute();
        attribute.mergeAttribute(this.immediatelyAttribute);
        attribute.mergeAttribute(this.foreverAttribute);
        attribute.mergeAttribute(this.continueAttribute);
        float hurt = -attribute.getAttribute(AttributeType.HP);
        if (hurt > 0) {
            hurt = Math.max(0, hurt - attribute.getAttribute(AttributeType.SAVE_HP));
            attribute.setAttribute(AttributeType.HP, -hurt);
        }
        return attribute;
    }
}

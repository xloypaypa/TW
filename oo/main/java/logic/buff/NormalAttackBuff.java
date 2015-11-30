package logic.buff;

import logic.attribute.Attribute;
import logic.attribute.AttributeType;

/**
 * Created by xlo on 15/11/29.
 * it's the normal attack buff
 */
public class NormalAttackBuff extends AbstractImmediatelyBuff {

    public NormalAttackBuff(float effect) {
        super(effect);
    }

    @Override
    public Attribute getEffect() {
        Attribute attribute = new Attribute();
        attribute.setAttribute(AttributeType.HP, -effect);
        return attribute;
    }
}

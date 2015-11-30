package logic.buff;

import logic.attribute.Attribute;
import logic.attribute.AttributeType;

/**
 * Created by xlo on 15/11/30.
 * it's the defence buff
 */
public class DefenceBuff extends AbstractImmediatelyBuff {

    public DefenceBuff(float effect) {
        super(effect);
    }

    @Override
    public Attribute getEffect() {
        Attribute attribute = new Attribute();
        attribute.setAttribute(AttributeType.SAVE_HP, effect);
        return attribute;
    }
}

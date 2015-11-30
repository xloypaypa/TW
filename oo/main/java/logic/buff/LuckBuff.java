package logic.buff;

import logic.attribute.Attribute;
import logic.attribute.AttributeType;

/**
 * Created by xlo on 15/11/30.
 * it's the luck buff
 */
public class LuckBuff extends AbstractImmediatelyBuff {

    public LuckBuff() {
        super(3);
    }

    @Override
    public Attribute getEffect() {
        Attribute attribute = new Attribute();
        attribute.setAttribute(AttributeType.LUCK, effect);
        return attribute;
    }
}

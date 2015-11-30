package logic.buff;

import logic.attribute.Attribute;
import logic.attribute.AttributeType;

/**
 * Created by xlo on 15/11/30.
 * it's the fire buff
 */
public class FireBuff extends AbstractContinueBuff {

    public FireBuff(int round) {
        super(round);
    }

    @Override
    public Attribute getEffect() {
        Attribute attribute = new Attribute();
        attribute.setAttribute(AttributeType.FIRE, 1);
        return attribute;
    }
}

package logic.buff;

import logic.attribute.Attribute;
import logic.attribute.AttributeType;

/**
 * Created by xlo on 15/11/30.
 * it's the cold buff
 */
public class ColdBuff extends AbstractContinueBuff {

    public ColdBuff(int round) {
        super(round);
    }

    @Override
    public Attribute getEffect() {
        Attribute attribute = new Attribute();
        attribute.setAttribute(AttributeType.COLD, 1);
        return attribute;
    }
}

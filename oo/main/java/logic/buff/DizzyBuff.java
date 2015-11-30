package logic.buff;

import logic.attribute.Attribute;
import logic.attribute.AttributeType;

/**
 * Created by xlo on 15/11/30.
 * it's the dizzy buff
 */
public class DizzyBuff extends AbstractContinueBuff {
    public DizzyBuff(int round) {
        super(round);
    }

    @Override
    public Attribute getEffect() {
        Attribute attribute = new Attribute();
        attribute.setAttribute(AttributeType.DIZZY, 1);
        return attribute;
    }
}

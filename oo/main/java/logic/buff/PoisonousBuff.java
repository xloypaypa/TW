package logic.buff;

import logic.attribute.Attribute;
import logic.attribute.AttributeType;

/**
 * Created by xlo on 15/11/30.
 * it's the poisonous buff
 */
public class PoisonousBuff extends AbstractContinueBuff {

    public PoisonousBuff(int round) {
        super(round);
    }

    @Override
    public Attribute getEffect() {
        Attribute attribute = new Attribute();
        attribute.setAttribute(AttributeType.POISONOUS, 1);
        return attribute;
    }
}

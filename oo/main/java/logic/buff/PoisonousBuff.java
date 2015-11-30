package logic.buff;

import logic.attribute.Attribute;
import logic.attribute.AttributeType;

/**
 * Created by xlo on 15/11/30.
 * it's the poisonous buff
 */
public class PoisonousBuff extends AbstractContinueBuff {

    protected float effect;

    public PoisonousBuff(int round, float effect) {
        super(round);
        this.effect = effect;
    }

    @Override
    public Attribute getEffect() {
        Attribute attribute = new Attribute();
        attribute.setAttribute(AttributeType.POISONOUS, effect);
        return attribute;
    }
}

package logic.buff;

import logic.attribute.Attribute;
import logic.attribute.AttributeType;

/**
 * Created by xlo on 15/11/30.
 * it's the fire buff
 */
public class FireBuff extends AbstractContinueBuff {

    protected float effect;

    public FireBuff(int round, float effect) {
        super(round);
        this.effect = effect;
    }

    @Override
    public Attribute getEffect() {
        Attribute attribute = new Attribute();
        attribute.setAttribute(AttributeType.FIRE, effect);
        return attribute;
    }
}

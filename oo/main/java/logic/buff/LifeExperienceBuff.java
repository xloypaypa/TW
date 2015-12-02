package logic.buff;

import logic.attribute.Attribute;
import logic.attribute.AttributeType;

/**
 * Created by xlo on 2015/12/2.
 * 一点人生的经验
 */
public class LifeExperienceBuff extends AbstractContinueBuff {

    private final float effect;

    public LifeExperienceBuff(int round, float effect) {
        super(round);
        this.effect = effect;
    }

    @Override
    public Attribute getEffect() {
        Attribute attribute = new Attribute();
        attribute.setAttribute(AttributeType.LIFE_EXPERIENCE, effect);
        return attribute;
    }
}

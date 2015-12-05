package logic.buff;

import logic.attribute.Attribute;
import logic.attribute.AttributeType;

/**
 * Created by xlo on 15/11/30.
 * it's the cold buff
 */
public class ColdBuff extends AbstractContinueBuff {

    private int time;

    public ColdBuff(int round) {
        super(round);
        this.time = 0;
    }

    @Override
    public void effected() {
        super.effected();
        this.time++;
    }

    public boolean isDizzy() {
        return time % 3 == 2;
    }

    @Override
    public Attribute getEffect() {
        Attribute attribute = new Attribute();
        attribute.setAttribute(AttributeType.COLD, 1);
        return attribute;
    }
}

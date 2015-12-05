package logic.buff;

import logic.attribute.Attribute;
import logic.attribute.AttributeType;

/**
 * Created by xlo on 2015/12/2.
 * 续一秒
 */
public class ContinuedOneSecond extends AbstractContinueBuff {

    public ContinuedOneSecond() {
        super(Integer.MAX_VALUE);
    }

    @Override
    public Attribute getEffect() {
        Attribute attribute = new Attribute();
        attribute.setAttribute(AttributeType.CONTINUE_ONE_SECOND, -1);
        return attribute;
    }

}

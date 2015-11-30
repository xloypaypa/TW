package logic.buff;

/**
 * Created by xlo on 15/11/29.
 * it's the abstract immediately buff
 */
public abstract class AbstractImmediatelyBuff implements ImmediatelyBuff {

    protected float effect;

    protected AbstractImmediatelyBuff(float effect) {
        this.effect = effect;
    }
}

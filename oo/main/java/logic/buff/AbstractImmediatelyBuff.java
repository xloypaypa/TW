package logic.buff;

/**
 * Created by xlo on 15/11/29.
 * it's the abstract immediately buff
 */
abstract class AbstractImmediatelyBuff implements ImmediatelyBuff {

    final float effect;

    AbstractImmediatelyBuff(float effect) {
        this.effect = effect;
    }
}

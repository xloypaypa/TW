package logic.buff;

/**
 * Created by xlo on 15/11/30.
 * it's the abstract continue buff
 */
public abstract class AbstractContinueBuff implements ContinueBuff {

    protected int round;

    public AbstractContinueBuff(int round) {
        this.round = round;
    }

    @Override
    public int remainRound() {
        return this.round;
    }

    @Override
    public void effected() {
        this.round--;
    }
}

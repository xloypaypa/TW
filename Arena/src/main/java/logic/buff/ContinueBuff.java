package logic.buff;

/**
 * Created by xlo on 15/11/29.
 * it's the continue buff
 */
public interface ContinueBuff extends Buff {

    int remainRound();

    void effected();

    void addRound(int round);

}

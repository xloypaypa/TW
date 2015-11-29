package logic.buff;

/**
 * Created by xlo on 15/11/29.
 * it's the buff pack
 */
public class BuffPackage implements Buff {

    protected float effect;

    public BuffPackage() {
        this.effect = 0;
    }

    public void addBuff(Buff buff) {
        this.effect += buff.getEffect();
    }

    @Override
    public float getEffect() {
        return this.effect;
    }
}

package logic.buff;

import logic.attribute.Attribute;

/**
 * Created by xlo on 15/11/29.
 * it's the buff pack
 */
public class BuffPackage implements Buff {

    protected Attribute attribute;

    public BuffPackage() {
        this.attribute = new Attribute();
    }

    public void addBuff(Buff buff) {
        attribute.mergeAttribute(buff.getEffect());
    }

    @Override
    public Attribute getEffect() {
        return this.attribute;
    }
}

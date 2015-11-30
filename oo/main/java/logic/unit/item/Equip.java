package logic.unit.item;

import logic.buff.BuffPackage;
import logic.buff.DefenceBuff;
import logic.unit.DefenceAble;

/**
 * Created by xlo on 15/11/29.
 * it's the equip
 */
public class Equip implements DefenceAble {

    protected String name;
    protected float defence;

    public Equip(String name, float defence) {
        this.defence = defence;
        this.name = name;
    }

    @Override
    public BuffPackage getDefence() {
        BuffPackage buffPackage = new BuffPackage();
        buffPackage.addImmediatelyBuff(new DefenceBuff(defence));
        return buffPackage;
    }
}

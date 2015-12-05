package logic.unit.item;

import logic.buff.buffPackage.BuffFromMessage;
import logic.buff.buffPackage.BuffPackage;
import logic.buff.DefenceBuff;
import logic.unit.DefenceAble;

/**
 * Created by xlo on 15/11/29.
 * it's the equip
 */
public class Equip implements DefenceAble {

    private final String name;
    private final float defence;

    public Equip(String name, float defence) {
        this.defence = defence;
        this.name = name;
    }

    @Override
    public BuffPackage getDefence() {
        BuffPackage buffPackage = new BuffPackage();
        BuffFromMessage buffFromMessage = new BuffFromMessage();
        buffFromMessage.addBuffFrom(this.getClass(), this.name);
        buffPackage.addImmediatelyBuff(buffFromMessage, new DefenceBuff(defence));
        return buffPackage;
    }
}

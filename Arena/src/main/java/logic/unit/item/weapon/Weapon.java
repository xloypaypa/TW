package logic.unit.item.weapon;

import logic.buff.buffPackage.BuffFromMessage;
import logic.buff.buffPackage.BuffPackage;
import logic.buff.NormalAttackBuff;
import logic.unit.AttackAble;

/**
 * Created by xlo on 15/11/29.
 * it's the weapon
 */
public class Weapon implements AttackAble {

    private final String name;
    private final float attack;

    Weapon(String name, float attack) {
        this.name = name;
        this.attack = attack;
    }

    @Override
    public BuffPackage getAttack() {
        BuffPackage buffPackage = new BuffPackage();
        BuffFromMessage buffFromMessage = getBuffFromMessage();
        buffPackage.addImmediatelyBuff(buffFromMessage, new NormalAttackBuff(attack));
        return buffPackage;
    }

    public float getDirectAttack() {
        return this.attack;
    }

    public String getName() {
        return name;
    }

    protected BuffFromMessage getBuffFromMessage() {
        BuffFromMessage buffFromMessage = new BuffFromMessage();
        buffFromMessage.addBuffFrom(this);
        return buffFromMessage;
    }
}

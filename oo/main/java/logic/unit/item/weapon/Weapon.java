package logic.unit.item.weapon;

import logic.buff.BuffPackage;
import logic.buff.NormalAttackBuff;
import logic.unit.AttackAble;

/**
 * Created by xlo on 15/11/29.
 * it's the weapon
 */
public class Weapon implements AttackAble {

    protected String name;
    protected float attack;

    public Weapon(String name, float attack) {
        this.name = name;
        this.attack = attack;
    }

    @Override
    public BuffPackage getAttack() {
        BuffPackage buffPackage = new BuffPackage();
        buffPackage.addImmediatelyBuff(new NormalAttackBuff(attack));
        return buffPackage;
    }

    public float getDirectAttack() {
        return this.attack;
    }

    public String getName() {
        return name;
    }
}

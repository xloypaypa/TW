package logic.unit.item.weapon.impl;

import logic.buff.BuffPackage;
import logic.buff.FireBuff;
import logic.unit.item.weapon.LongWeapon;

import java.util.Random;

/**
 * Created by xlo on 2015/12/1.
 * it's the long fire weapon
 */
public class LongFireWeapon extends LongWeapon {
    protected Random random;

    public LongFireWeapon(float attack, Random random) {
        super("long fire", attack);
        this.random = random;
    }

    @Override
    public BuffPackage getAttack() {
        BuffPackage attack = super.getAttack();
        if (this.random.nextInt(100) > 50) {
            attack.addContinueBuff(new FireBuff(9, 1));
        }
        return attack;
    }
}

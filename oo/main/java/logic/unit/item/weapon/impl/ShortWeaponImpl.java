package logic.unit.item.weapon.impl;

import logic.buff.BuffPackage;
import logic.buff.PoisonousBuff;
import logic.unit.item.weapon.ShortWeapon;

import java.util.Random;

/**
 * Created by xlo on 15/11/30.
 * it's the short weapon impl
 */
public class ShortWeaponImpl extends ShortWeapon {
    protected Random random;

    public ShortWeaponImpl(float attack, Random random) {
        super("short", attack);
        this.random = random;
    }

    @Override
    public BuffPackage getAttack() {
        BuffPackage attack = super.getAttack();
        if (this.random.nextInt(100) > 50) {
            attack.addContinueBuff(new PoisonousBuff(3, 1));
        }
        return attack;
    }
}

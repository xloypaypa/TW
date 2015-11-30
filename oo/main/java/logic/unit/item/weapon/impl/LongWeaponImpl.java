package logic.unit.item.weapon.impl;

import logic.buff.BuffPackage;
import logic.buff.ColdBuff;
import logic.unit.item.weapon.LongWeapon;

import java.util.Random;

/**
 * Created by xlo on 15/11/30.
 * it's the long weapon
 */
public class LongWeaponImpl extends LongWeapon {
    protected Random random;

    public LongWeaponImpl(float attack, Random random) {
        super("long", attack);
        this.random = random;
    }

    @Override
    public BuffPackage getAttack() {
        BuffPackage attack = super.getAttack();
        if (this.random.nextInt(100) > 50) {
            attack.addContinueBuff(new ColdBuff(3));
        }
        return attack;
    }
}

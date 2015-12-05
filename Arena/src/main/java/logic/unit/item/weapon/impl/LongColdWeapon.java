package logic.unit.item.weapon.impl;

import logic.buff.BuffPackage;
import logic.buff.ColdBuff;
import logic.unit.item.weapon.LongWeapon;

import java.util.Random;

/**
 * Created by xlo on 15/11/30.
 * it's the long weapon
 */
public class LongColdWeapon extends LongWeapon {
    private final Random random;

    public LongColdWeapon(float attack, Random random) {
        super("long cold", attack);
        this.random = random;
    }

    @Override
    public BuffPackage getAttack() {
        BuffPackage attack = super.getAttack();
        if (this.random.nextInt(100) > 50) {
            attack.addContinueBuff(new ColdBuff(6));
        }
        return attack;
    }
}

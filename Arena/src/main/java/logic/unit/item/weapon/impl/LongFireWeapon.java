package logic.unit.item.weapon.impl;

import logic.buff.buffPackage.BuffPackage;
import logic.buff.FireBuff;
import logic.unit.item.weapon.LongWeapon;

import java.util.Random;

/**
 * Created by xlo on 2015/12/1.
 * it's the long fire weapon
 */
public class LongFireWeapon extends LongWeapon {
    private final Random random;

    public LongFireWeapon(float attack, Random random) {
        super("long fire", attack);
        this.random = random;
    }

    @Override
    public BuffPackage getAttack() {
        BuffPackage attack = super.getAttack();
        if (this.random.nextInt(100) > 50) {
            attack.addContinueBuff(this.getBuffFromMessage(), new FireBuff(9, 1));
        }
        return attack;
    }
}

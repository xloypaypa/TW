package logic.unit.item.weapon.impl;

import logic.buff.buffPackage.BuffPackage;
import logic.buff.PoisonousBuff;
import logic.unit.item.weapon.ShortWeapon;

import java.util.Random;

/**
 * Created by xlo on 15/11/30.
 * it's the short weapon impl
 */
public class ShortPoisonousWeapon extends ShortWeapon {
    private final Random random;

    public ShortPoisonousWeapon(float attack, Random random) {
        super("short poisonous", attack);
        this.random = random;
    }

    @Override
    public BuffPackage getAttack() {
        BuffPackage attack = super.getAttack();
        if (this.random.nextInt(100) > 50) {
            attack.addContinueBuff(this.getBuffFromMessage(), new PoisonousBuff(3, 2));
        }
        return attack;
    }
}

package logic.unit.item.weapon.impl;

import logic.buff.BuffPackage;
import logic.buff.DizzyBuff;
import logic.unit.item.weapon.NormalWeapon;

import java.util.Random;

/**
 * Created by xlo on 2015/12/1.
 * it's the normal dizzy
 */
public class NormalDizzyWeapon extends NormalWeapon {
    private final Random random;

    public NormalDizzyWeapon(float attack, Random random) {
        super("normal dizzy", attack);
        this.random = random;
    }

    @Override
    public BuffPackage getAttack() {
        BuffPackage attack = super.getAttack();
        if (this.random.nextInt(100) > 80) {
            attack.addContinueBuff(new DizzyBuff(3));
        }
        return attack;
    }

}

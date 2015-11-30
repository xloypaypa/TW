package logic.unit.item.weapon.impl;

import logic.buff.BuffPackage;
import logic.buff.LuckBuff;
import logic.unit.item.weapon.NormalWeapon;

import java.util.Random;

/**
 * Created by xlo on 15/11/30.
 * it's the normal weapon impl
 */
public class NormalWeaponImpl extends NormalWeapon {
    protected Random random;

    public NormalWeaponImpl(float attack, Random random) {
        super("normal", attack);
        this.random = random;
    }

    @Override
    public BuffPackage getAttack() {
        BuffPackage attack = super.getAttack();
        if (this.random.nextInt(100) > 50) {
            attack.addImmediatelyBuff(new LuckBuff(3));
        }
        return attack;
    }
}

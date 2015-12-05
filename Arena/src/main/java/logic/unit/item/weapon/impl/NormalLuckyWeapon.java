package logic.unit.item.weapon.impl;

import logic.buff.buffPackage.BuffPackage;
import logic.buff.LuckBuff;
import logic.unit.item.weapon.NormalWeapon;

import java.util.Random;

/**
 * Created by xlo on 15/11/30.
 * it's the normal weapon impl
 */
public class NormalLuckyWeapon extends NormalWeapon {
    private final Random random;

    public NormalLuckyWeapon(float attack, Random random) {
        super("normal lucky", attack);
        this.random = random;
    }

    @Override
    public BuffPackage getAttack() {
        BuffPackage attack = super.getAttack();
        if (this.random.nextInt(100) > 75) {
            attack.addImmediatelyBuff(this.getBuffFromMessage(), new LuckBuff(3));
        }
        return attack;
    }
}

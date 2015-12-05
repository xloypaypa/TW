package logic.unit.item.weapon.impl;

import logic.buff.buffPackage.BuffPackage;
import logic.buff.LuckBuff;
import logic.unit.item.weapon.ShortWeapon;

import java.util.Random;

/**
 * Created by xlo on 2015/12/1.
 * it's the lucky weapon
 */
public class ShortLuckyWeapon extends ShortWeapon {
    private final Random random;

    public ShortLuckyWeapon(float attack, Random random) {
        super("short lucky", attack);
        this.random = random;
    }

    @Override
    public BuffPackage getAttack() {
        BuffPackage attack = super.getAttack();
        if (this.random.nextInt(100) > 25) {
            attack.addImmediatelyBuff(this.getBuffFromMessage(), new LuckBuff(3));
        }
        return attack;
    }
}

package logic.unit.item.weapon;

import logic.buff.buffPackage.BuffPackage;
import logic.buff.LifeExperienceBuff;

import java.util.Random;

/**
 * Created by xlo on 2015/12/2.
 * 任何武器还是要按照基本法
 */
public class BasicLawWeapon extends Weapon {

    private final Random random;

    public BasicLawWeapon(float attack, Random random) {
        super("基本法", attack);
        this.random = random;
    }

    @Override
    public BuffPackage getAttack() {
        BuffPackage attack = super.getAttack();
        if (this.random.nextInt(100) > 25) {
            attack.addContinueBuff(this.getBuffFromMessage(), new LifeExperienceBuff(5, 1));
        }
        return attack;
    }
}

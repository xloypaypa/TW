package logic.unit.player;

import logic.buff.BuffPackage;
import logic.job.JobType;
import logic.unit.item.weapon.ShortWeapon;
import logic.unit.item.weapon.Weapon;

/**
 * Created by xlo on 2015/12/1.
 * it's the killer
 */
public class KillerPlayer extends NormalPlayer{

    public KillerPlayer(String name, float hp, float attack) {
        super(name, hp, attack, JobType.KILLER);
    }

    @Override
    public void setWeapon(Weapon weapon) throws Exception {
        if (!(weapon instanceof ShortWeapon)) {
            throw new Exception();
        }
        super.setWeapon(weapon);
    }

    @Override
    protected void getWeaponAttack(BuffPackage buffPackage) {
        if (this.getWeapon() instanceof ShortWeapon) {
            buffPackage.addBuffPackage(this.getWeapon().getAttack());
        } else {
            super.getWeaponAttack(buffPackage);
        }
    }

}

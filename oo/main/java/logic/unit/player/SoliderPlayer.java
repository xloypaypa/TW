package logic.unit.player;

import logic.buff.BuffPackage;
import logic.job.JobType;
import logic.unit.item.weapon.NormalWeapon;
import logic.unit.item.weapon.Weapon;

/**
 * Created by xlo on 15/11/29.
 * it's the solider
 */
public class SoliderPlayer extends NormalPlayer {

    public SoliderPlayer(String name, float hp, float attack) {
        super(name, hp, attack, JobType.SOLIDER);
    }

    @Override
    public void setWeapon(Weapon weapon) throws Exception {
        if (!(weapon instanceof NormalWeapon)) {
            throw new Exception();
        }
        super.setWeapon(weapon);
    }

    @Override
    protected void getWeaponAttack(BuffPackage buffPackage) {
        if (this.getWeapon() instanceof NormalWeapon) {
            buffPackage.addBuffPackage(this.getWeapon().getAttack());
        } else {
            super.getWeaponAttack(buffPackage);
        }
    }

}

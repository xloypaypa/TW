package logic.unit.player;

import logic.buff.BuffPackage;
import logic.buff.ContinuedOneSecond;
import logic.job.JobType;
import logic.unit.item.weapon.Weapon;

/**
 * Created by xlo on 2015/12/2.
 * 作为一个长者
 */
public class ElderlyPlayer extends NormalPlayer {

    public ElderlyPlayer(String name, float hp, float attack) {
        super(name, hp, attack, JobType.ELDERLY);
        BuffPackage buffPackage = new BuffPackage();
        buffPackage.addContinueBuff(new ContinuedOneSecond());
        this.attachBuff(buffPackage);
    }

    @Override
    public void setWeapon(Weapon weapon) throws Exception {
        super.setWeapon(weapon);
    }

    @Override
    protected void getWeaponAttack(BuffPackage buffPackage) {
        if (this.getWeapon() == null) return;
        buffPackage.addBuffPackage(this.getWeapon().getAttack());
    }

}

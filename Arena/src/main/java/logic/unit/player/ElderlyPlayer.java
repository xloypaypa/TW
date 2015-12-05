package logic.unit.player;

import logic.buff.BuffPackage;
import logic.buff.ContinuedOneSecond;
import logic.buff.PlayerBuffPackage;
import logic.job.JobType;
import logic.log.GameLog;
import logic.unit.item.weapon.Weapon;

/**
 * Created by xlo on 2015/12/2.
 * 作为一个长者
 */
public class ElderlyPlayer extends NormalPlayer {

    public static ElderlyPlayer getNewNormalPlayer(String name, float hp, float attack) {
        ElderlyPlayer elderlyPlayer = new ElderlyPlayer(name, hp, attack);
        elderlyPlayer.setBuffPackage(new PlayerBuffPackage(elderlyPlayer, GameLog.getGameLog()));
        BuffPackage buffPackage = new BuffPackage();
        buffPackage.addContinueBuff(new ContinuedOneSecond());
        elderlyPlayer.attachBuff(buffPackage);
        return elderlyPlayer;
    }

    ElderlyPlayer(String name, float hp, float attack) {
        super(name, hp, attack, JobType.ELDERLY);
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

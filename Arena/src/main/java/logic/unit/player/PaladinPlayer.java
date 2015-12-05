package logic.unit.player;

import logic.buff.buffPackage.BuffPackage;
import logic.buff.buffPackage.PlayerBuffPackage;
import logic.job.JobType;
import logic.log.GameLog;
import logic.unit.item.weapon.LongWeapon;
import logic.unit.item.weapon.ShortWeapon;
import logic.unit.item.weapon.Weapon;

/**
 * Created by xlo on 2015/12/1.
 * it's the paladin player
 */
public class PaladinPlayer extends NormalPlayer {

    public static PaladinPlayer getNewPaladinPlayer(String name, float hp, float attack) {
        PaladinPlayer paladinPlayer = new PaladinPlayer(name, hp, attack);
        paladinPlayer.setBuffPackage(new PlayerBuffPackage(paladinPlayer, GameLog.getGameLog()));
        return paladinPlayer;
    }

    PaladinPlayer(String name, float hp, float attack) {
        super(name, hp, attack, JobType.PALADIN);
    }

    @Override
    public void setWeapon(Weapon weapon) throws Exception {
        if (weapon instanceof ShortWeapon) {
            throw new Exception();
        }
        super.setWeapon(weapon);
    }

    @Override
    protected void getWeaponAttack(BuffPackage buffPackage) {
        if (this.getWeapon() == null) return;
        if (this.getWeapon() instanceof LongWeapon) {
            buffPackage.addBuffPackage(this.getWeapon().getAttack());
        } else {
            super.getWeaponAttack(buffPackage);
        }
    }

}

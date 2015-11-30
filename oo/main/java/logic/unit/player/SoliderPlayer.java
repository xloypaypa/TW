package logic.unit.player;

import logic.buff.BuffPackage;
import logic.job.DefaultJob;
import logic.job.JobType;
import logic.log.GameLog;
import logic.unit.item.Equip;
import logic.unit.item.Weapon;

/**
 * Created by xlo on 15/11/29.
 * it's the solider
 */
public class SoliderPlayer extends NormalPlayer {

    protected Weapon weapon;
    protected Equip equip;

    public SoliderPlayer(String name, float hp, float attack, GameLog gameLog) {
        super(name, hp, attack);
        //FIXME job 不应该被初始化两边吧?
        this.job = new DefaultJob(JobType.SOLIDER);
    }

    @Override
    public BuffPackage getAttack() {
        BuffPackage buffPackage = super.getAttack();
        if (this.weapon != null) {
            buffPackage.addImmediatelyBuff(this.weapon.getAttack());
        }
        return buffPackage;
    }

    public void setEquip(Equip equip) {
        this.equip = equip;
        this.buffPackage.addForeverAttribute(equip.getDefence());
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Equip getEquip() {
        return equip;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}

package logic.unit.player;

import logic.attribute.Attribute;
import logic.attribute.AttributeType;
import logic.buff.BuffPackage;
import logic.buff.NormalAttackBuff;
import logic.job.DefaultJob;
import logic.job.Job;
import logic.job.JobType;
import logic.unit.item.Equip;
import logic.unit.item.weapon.Weapon;

/**
 * Created by xlo on 15/11/29.
 * it's the normal player
 */
public class NormalPlayer implements Player {

    protected String name;
    protected Attribute attribute;
    protected BuffPackage buffPackage;
    protected Job job;
    protected Weapon weapon;
    protected Equip equip;

    public NormalPlayer(String name, float hp, float attack) {
        this(name, hp, attack, JobType.NORMAL);
    }

    protected NormalPlayer(String name, float hp, float attack, JobType jobType) {
        this.name = name;

        this.attribute = new Attribute();
        this.attribute.setAttribute(AttributeType.HP, hp);
        this.attribute.setAttribute(AttributeType.ATTACK, attack);

        this.job = new DefaultJob(jobType);

        this.buffPackage = new BuffPackage();
    }

    @Override
    public Attribute getAttribute() {
        return this.attribute;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void attachBuff(BuffPackage buffPackage) {
        this.buffPackage.addBuffPackage(buffPackage);
    }

    @Override
    public void immediatelyBuffToAttribute() {
        this.attribute.mergeAttribute(this.buffPackage.getImmediatelyEffect());
        this.buffPackage.clearImmediatelyBuff();
    }

    @Override
    public BuffPackage getBuff() {
        return this.buffPackage;
    }

    @Override
    public boolean isAlive() {
        return this.attribute.getAttribute(AttributeType.HP) >= 0;
    }

    @Override
    public String getJobName() {
        return this.job.getJobName();
    }

    @Override
    public BuffPackage getAttack() {
        BuffPackage buffPackage = new BuffPackage();
        buffPackage.addImmediatelyBuff(new NormalAttackBuff(this.attribute.getAttribute(AttributeType.ATTACK)));
        getWeaponAttack(buffPackage);
        return buffPackage;
    }

    @Override
    public void setEquip(Equip equip) {
        this.equip = equip;
    }

    @Override
    public void setWeapon(Weapon weapon) throws Exception {
        this.weapon = weapon;
    }

    @Override
    public Equip getEquip() {
        return equip;
    }

    @Override
    public Weapon getWeapon() {
        return weapon;
    }

    protected void getWeaponAttack(BuffPackage buffPackage) {
        if (this.getWeapon() != null) {
            BuffPackage weapon = new BuffPackage();
            weapon.addImmediatelyBuff(new NormalAttackBuff(this.getWeapon().getDirectAttack()));
            buffPackage.addBuffPackage(weapon);
        }
    }
}

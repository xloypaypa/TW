package logic.unit.player;

import logic.attribute.Attribute;
import logic.attribute.AttributeType;
import logic.buff.Buff;
import logic.buff.BuffPackage;
import logic.buff.NormalAttackBuff;
import logic.job.DefaultJob;
import logic.job.Job;
import logic.job.JobType;

/**
 * Created by xlo on 15/11/29.
 * it's the normal player
 */
public class NormalPlayer implements Player {

    protected String name;
    protected Attribute attribute;
    protected BuffPackage buffPackage;
    protected Job job;

    public NormalPlayer(String name, float hp, float attack) {
        this.name = name;

        this.attribute = new Attribute();
        this.attribute.setAttribute(AttributeType.HP, hp);
        this.attribute.setAttribute(AttributeType.ATTACK, attack);

        this.job = new DefaultJob(JobType.NORMAL);

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
    public void attachBuff(Buff buff) {
        this.buffPackage.addImmediatelyBuff(buff);
    }

    @Override
    public void buffToAttribute() {
        this.attribute.mergeAttribute(this.buffPackage.getEffect());
        this.buffPackage.clearImmediatelyBuff();
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
        return buffPackage;
    }
}

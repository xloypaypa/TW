package logic.unit.player;

import logic.attribute.Attribute;
import logic.buff.Buff;
import logic.buff.BuffPackage;
import logic.job.Job;
import logic.unit.AttackAble;

/**
 * Created by xlo on 15/11/29.
 * it's the interface for player
 */
public interface Player extends AttackAble, Job {

    Attribute getAttribute();

    String getName();

    void attachBuff(Buff buff);

    void buffToAttribute();

    boolean isAlive();

}

package logic.unit.player;

import logic.attribute.Attribute;
import logic.buff.BuffPackage;
import logic.buff.PlayerBuffPackage;
import logic.job.Job;
import logic.unit.AttackAble;
import logic.unit.Bag;

/**
 * Created by xlo on 15/11/29.
 * it's the interface for player
 */
public interface Player extends AttackAble, Job, Bag {

    Attribute getAttribute();

    String getName();

    void attachBuff(BuffPackage buffPackage);

    PlayerBuffPackage getPlayerBuff();

    boolean isAlive();

}

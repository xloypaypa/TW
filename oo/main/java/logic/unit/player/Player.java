package logic.unit.player;

import logic.job.Job;
import logic.unit.AttackAble;

/**
 * Created by xlo on 15/11/29.
 * it's the interface for player
 */
public interface Player extends AttackAble, Job {

    float getHp();

    String getName();

    void beAttacked(Player player);

    float calculateHurt(Player player);

    void changeHp(float value);

    boolean isAlive();

}

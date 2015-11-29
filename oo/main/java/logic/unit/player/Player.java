package logic.unit.player;

import logic.unit.AttackAble;

/**
 * Created by xlo on 15/11/29.
 * it's the interface for player
 */
public interface Player extends AttackAble {

    double getHp();

    String getName();

    void beAttacked(AttackAble attackAble);

    boolean isAlive();

}

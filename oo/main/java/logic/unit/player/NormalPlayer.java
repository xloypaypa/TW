package logic.unit.player;

import logic.unit.AttackAble;

/**
 * Created by xlo on 15/11/29.
 * it's the normal player
 */
public class NormalPlayer implements Player {

    protected String name;
    protected float hp, attack;

    public NormalPlayer(String name, float hp, float attack) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
    }

    @Override
    public float getAttack() {
        return this.attack;
    }

    @Override
    public double getHp() {
        return this.hp;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void beAttacked(AttackAble attackAble) {
        this.hp -= attackAble.getAttack();
    }

    @Override
    public boolean isAlive() {
        return this.hp >= 0;
    }
}

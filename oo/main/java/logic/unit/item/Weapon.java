package logic.unit.item;

import logic.unit.AttackAble;

/**
 * Created by xlo on 15/11/29.
 * it's the weapon
 */
public class Weapon implements AttackAble {

    protected String name;
    protected float attack;

    public Weapon(String name, float attack) {
        this.name = name;
        this.attack = attack;
    }

    @Override
    public float getAttack() {
        return this.attack;
    }

    public String getName() {
        return name;
    }
}

package logic.unit.item;

import logic.unit.AttackAble;
import logic.unit.DefenceAble;

/**
 * Created by xlo on 15/11/29.
 * it's the equip
 */
public class Equip implements DefenceAble {

    protected String name;
    protected float defence;

    public Equip(String name, float defence) {
        this.defence = defence;
        this.name = name;
    }

    @Override
    public float hurtingAfterDefence(AttackAble attackAble) {
        return Math.max(0, attackAble.getAttack() - defence);
    }
}

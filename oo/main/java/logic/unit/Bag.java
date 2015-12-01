package logic.unit;

import logic.unit.item.Equip;
import logic.unit.item.weapon.Weapon;

/**
 * Created by xlo on 2015/12/1.
 * it's the bag
 */
public interface Bag {
    void setEquip(Equip equip);

    void setWeapon(Weapon weapon) throws Exception;

    Equip getEquip();

    Weapon getWeapon();
}

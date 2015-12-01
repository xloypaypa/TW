package logic.unit.player;

import logic.buff.BuffPackage;
import logic.unit.item.weapon.LongWeapon;
import logic.unit.item.weapon.Weapon;

/**
 * Created by xlo on 2015/12/1.
 * it's the knight player
 */
public class KnightPlayer extends NormalPlayer {

    public KnightPlayer(String name, float hp, float attack) {
        super(name, hp, attack);
    }

    @Override
    public void setWeapon(Weapon weapon) throws Exception {
        if (!(weapon instanceof LongWeapon)) {
            throw new Exception();
        }
        super.setWeapon(weapon);
    }

    @Override
    protected void getWeaponAttack(BuffPackage buffPackage) {
        if (this.getWeapon() instanceof LongWeapon) {
            buffPackage.addBuffPackage(this.getWeapon().getAttack());
        } else {
            super.getWeaponAttack(buffPackage);
        }
    }

}

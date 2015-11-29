package logic.playerOld;

/**
 * Created by xlo on 15/11/28.
 * it's the soldier player
 */
public class Soldier extends PlayerWithJob {
    public Soldier(String name, double hp, double attack, String weapon, double weaponAttack, double defen) {
        super(name, hp, attack,"战士");
        this.weapon = weapon;
        this.weaponAttack = weaponAttack;
        this.defen = defen;
    }

    @Override
    public double getAttack() {
        return super.getAttack() + weaponAttack;
    }

    @Override
    protected double calculateAttack(Player player) {
        return Math.max(0, player.getAttack() - this.defen);
    }
}

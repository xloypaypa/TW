package logic;

/**
 * Created by xlo on 15/11/28.
 * it's the normal player
 */
public class Normal extends PlayerWithJob {

    public Normal(String name, double hp, double attack) {
        super(name, hp, attack,"普通人");
    }

    @Override
    protected double calculateAttack(Player player) {
        return player.getAttack();
    }
}

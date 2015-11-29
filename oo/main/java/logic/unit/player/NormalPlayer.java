package logic.unit.player;

import logic.log.GameLog;

/**
 * Created by xlo on 15/11/29.
 * it's the normal player
 */
public class NormalPlayer implements Player {

    protected String name;
    protected float hp, attack;
    protected GameLog gameLog;

    public NormalPlayer(String name, float hp, float attack, GameLog gameLog) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.gameLog = gameLog;
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
    public void beAttacked(Player player) {
        this.hp -= player.getAttack();
        gameLog.afterPlayerBeAttacked(this, player);
    }

    @Override
    public boolean isAlive() {
        return this.hp >= 0;
    }
}

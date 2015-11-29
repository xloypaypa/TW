package logic.unit.player;

import logic.job.DefaultJob;
import logic.job.Job;
import logic.job.JobType;
import logic.log.GameLog;

/**
 * Created by xlo on 15/11/29.
 * it's the normal player
 */
public class NormalPlayer implements Player {

    protected String name;
    protected float hp, attack;
    protected GameLog gameLog;
    protected Job job;

    public NormalPlayer(String name, float hp, float attack, GameLog gameLog) {
        this.name = name;
        this.job = new DefaultJob(JobType.NORMAL);
        this.hp = hp;
        this.attack = attack;
        this.gameLog = gameLog;
    }

    @Override
    public float getAttack() {
        return this.attack;
    }

    @Override
    public float getHp() {
        return this.hp;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void beAttacked(Player player) {
        this.hp -= calculateHurt(player);
        gameLog.afterPlayerBeAttacked(this, player);
    }

    @Override
    public float calculateHurt(Player player) {
        return player.getAttack();
    }

    @Override
    public boolean isAlive() {
        return this.hp >= 0;
    }

    @Override
    public String getJobName() {
        return this.job.getJobName();
    }
}

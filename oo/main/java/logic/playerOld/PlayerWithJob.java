package logic.playerOld;

/**
 * Created by xlo on 15/11/28.
 * it's the player who have job
 */
public abstract class PlayerWithJob extends Player {

    protected final String job;
    protected String weapon;
    protected double defen, weaponAttack;

    protected PlayerWithJob(String name, double hp, double attack, String job) {
        super(name, hp, attack);
        this.job = job;
    }

    public String getWeapon() {
        return weapon;
    }

    public String getJob() {
        return job;
    }

    @Override
    protected String buildLog(Player attacker) {
        PlayerWithJob playerWithJob = (PlayerWithJob) attacker;
        String ans = "";
        ans += playerWithJob.getJob();
        ans += this.getName();
        if (playerWithJob.getJob().equals("战士")) {
            ans += "用" + playerWithJob.getWeapon();
        }
        ans += "攻击了" + this.getName() + "," + this.getName() + "受到了" + this.calculateAttack(attacker) + "剩余: " + this.getHp();

        return ans;
    }

    @Override
    public String beAttack(Player player) {
        decHp(calculateAttack(player));
        return buildLog(player);
    }

    protected abstract double calculateAttack(Player player);
}

package logic;

import java.text.DecimalFormat;

/**
 * Created by xlo on 15/11/28.
 * it's the human
 */
public abstract class Player {

    private String name;
    private double hp, attack;

    public Player(String name, double hp, double attack) {
        this.hp = hp;
        this.name = name;
        this.attack = attack;
    }

    public double getHp() {
        return hp;
    }

    public String getName() {
        return name;
    }

    public double getAttack() {
        return attack;
    }

    public void decHp(double value) {
        this.hp -= value;
    }

    public String beAttack(Player player) {
        decHp(player.getAttack());
        return buildLog(player);
    }

    protected String buildLog(Player attacker) {
        DecimalFormat decimalFormat = new DecimalFormat("0");
        return String.format("%s攻击了%s,%s受到了%s点伤害,%s剩余%s",
                attacker.getName(), this.getName(), this.getName(),
                decimalFormat.format(attacker.getAttack()), this.getName(),
                decimalFormat.format(this.getHp()));
    }

    public boolean isDie() {
        return this.hp < 0;
    }
}

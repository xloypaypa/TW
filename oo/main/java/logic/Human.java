package logic;

/**
 * Created by xlo on 15/11/28.
 * it's the human
 */
public class Human {

    private String name;
    private double hp, attack;

    public Human(String name, double hp, double attack) {
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

    public boolean isDie() {
        return this.hp < 0;
    }
}

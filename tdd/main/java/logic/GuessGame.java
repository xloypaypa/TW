package logic;

import java.util.Random;

public class GuessGame {

    public static final String win = "Congratulation!";
    public static final String lose = "Game Over";
    public static final String inputHaveRepeat = "Cannot input duplicate numbers!";

    private int chance;
    private final String number;
    private boolean isEnded;

    public GuessGame(int chance, Random random) {
        this.chance = chance;
        this.isEnded = false;
        this.number = new NumberBuilder(random).buildNumber();
    }

    public String guess(String value) throws Exception {
        if (checkNumberDuplicate(value)) return inputHaveRepeat;

        String judgeResult = new NumberJudgment().judge(this.number, value);
        if (judgeResult.equals("4A0B")) {
            this.isEnded = true;
            return win;
        } else {
            this.chance--;
            if (chance == 0) {
                this.isEnded = true;
                return lose;
            }
        }
        return judgeResult;
    }

    public boolean isEnded() {
        return this.isEnded;
    }

    public String getHit() {
        return "Please input your number(" + this.chance + "):";
    }

    private boolean checkNumberDuplicate(String value) {
        boolean[] visited = new boolean[10];
        for (int i = 0; i < value.length(); i++) {
            int number = value.charAt(i) - '0';
            if (visited[number]) {
                return true;
            }
            visited[number] = true;
        }
        return false;
    }

}

package logic;

public class NumberJudgment {

    public static final String lengthError = "The number length should be 4.";
    public static final String repeatError = "The number should not have repeat.";

    private int resultA;
    private int resultB;

    public String judge(String from, String to) throws RuntimeException {
        if (from.length() != 4 || to.length() != 4) {
            throw new RuntimeException(lengthError);
        }

        checkRepeatNumber(from);
        checkRepeatNumber(to);

        resultA = 0;
        resultB = 0;
        compareNumber(from, to);
        return resultA + "A" + resultB + "B";
    }

    private void compareNumber(String a, String b) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    if (i == j) {
                        resultA++;
                    } else {
                        resultB++;
                    }
                }
            }
        }
    }

    private void checkRepeatNumber(String a) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == j) continue;
                if (a.charAt(i) == a.charAt(j)) {
                    throw new RuntimeException(repeatError);
                }
            }
        }
    }
}

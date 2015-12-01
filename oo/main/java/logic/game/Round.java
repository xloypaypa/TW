package logic.game;

import logic.unit.player.Player;

/**
 * Created by xlo on 15/11/30.
 * it's a round
 */
public abstract class Round {

    protected final Player attacker;
    protected final Player[] defender;
    private RoundStatus roundStatus;

    public Round(Player attacker, Player[] defender) {
        this.attacker = attacker;
        this.defender = defender;
        roundStatus = RoundStatus.ROUND_START;
    }

    public final void startARound() {
        while (roundStatus!=null) {
            if (roundStatus.equals(RoundStatus.ROUND_START)) {
                roundStatus = whenRoundStart();
            } else if (roundStatus.equals(RoundStatus.ACTION_START)) {
                roundStatus = whenActionStart();
            } else if (roundStatus.equals(RoundStatus.ACTION)) {
                roundStatus = whenAction();
            } else if (roundStatus.equals(RoundStatus.ACTION_END)) {
                roundStatus = whenActionEnd();
            } else if (roundStatus.equals(RoundStatus.ROUND_END)) {
                roundStatus = whenRoundEnd();
            }
        }
    }

    protected abstract RoundStatus whenRoundStart();

    protected abstract RoundStatus whenActionStart();

    protected abstract RoundStatus whenAction();

    protected abstract RoundStatus whenActionEnd();

    protected abstract RoundStatus whenRoundEnd();

    protected enum RoundStatus {
        ROUND_START, ACTION_START, ACTION, ACTION_END, ROUND_END
    }

}

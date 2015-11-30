package logic.game;

import logic.unit.player.Player;

/**
 * Created by xlo on 15/11/30.
 * it's the default round
 */
public class DefaultRound extends Round {

    public DefaultRound(Player attacker, Player[] defender) {
        super(attacker, defender);
    }

    @Override
    protected RoundStatus whenRoundStart() {
        return RoundStatus.ACTION_START;
    }

    @Override
    protected RoundStatus whenActionStart() {
        return RoundStatus.ACTION;
    }

    @Override
    protected RoundStatus whenAction() {
        for (Player now : defender) {
            now.beAttacked(attacker);
        }
        return RoundStatus.ACTION_END;
    }

    @Override
    protected RoundStatus whenActionEnd() {
        return RoundStatus.ROUND_END;
    }

    @Override
    protected RoundStatus whenRoundEnd() {
        return null;
    }
}

package logic.game;

import logic.attribute.AttributeType;
import logic.buff.BuffPackage;
import logic.log.GameLog;
import logic.unit.player.Player;
import logic.unit.player.SoliderPlayer;

/**
 * Created by xlo on 15/11/30.
 * it's the default round
 */
public class DefaultRound extends Round {

    protected float[] defenderOldHp;
    protected GameLog gameLog;

    public DefaultRound(Player attacker, Player[] defender, GameLog gameLog) {
        super(attacker, defender);
        this.defenderOldHp = new float[defender.length];
        this.gameLog = gameLog;
    }

    @Override
    protected RoundStatus whenRoundStart() {
        return RoundStatus.ACTION_START;
    }

    @Override
    protected RoundStatus whenActionStart() {
        for (Player now : defender) {
            if (now instanceof SoliderPlayer && ((SoliderPlayer) now).getEquip() != null) {
                now.attachBuff(((SoliderPlayer) now).getEquip().getDefence());
            }
        }
        for (int i = 0; i < defenderOldHp.length; i++) {
            defenderOldHp[i] = defender[i].getAttribute().getAttribute(AttributeType.HP);
        }
        return RoundStatus.ACTION;
    }

    @Override
    protected RoundStatus whenAction() {
        BuffPackage buffPackage = attacker.getAttack();
        for (Player now : defender) {
            now.attachBuff(buffPackage);
        }
        return RoundStatus.ACTION_END;
    }

    @Override
    protected RoundStatus whenActionEnd() {
        for (int i = 0; i < defender.length; i++) {
            Player now = defender[i];
            now.immediatelyBuffToAttribute();

            gameLog.afterPlayerBeAttacked(now.getName(), now.getJobName(),
                    defenderOldHp[i] - now.getAttribute().getAttribute(AttributeType.HP),
                    now.getAttribute().getAttribute(AttributeType.HP), attacker);
        }
        return RoundStatus.ROUND_END;
    }

    @Override
    protected RoundStatus whenRoundEnd() {
        return null;
    }
}

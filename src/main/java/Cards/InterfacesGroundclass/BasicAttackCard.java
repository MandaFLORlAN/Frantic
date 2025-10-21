package Cards.InterfacesGroundclass;

import Connector.Connector;
import Enums.Color;
import Game.GameState;

import java.util.List;

public abstract class BasicAttackCard extends Card {

    public BasicAttackCard(String name, int number, Color color) {
        super(name, number, 7, color);
    }

    protected String getTarget(String executor, String message, Connector connector, SpecialCard attackingCard, GameState gs) {
        String target = connector.getPlayerTargets(executor, message, 1).getFirst();
        if (connector.wantToBlock(target, message)) {
            attackingCard.executeSpecialFunction(target, connector, gs);
            return null;
        }
        return target;
    }

    protected List<String> getTargets(String executor, String message, Connector connector, SpecialCard attackingCard, GameState gs, int numberOfTargets) {
        List<String> targets = connector.getPlayerTargets(executor, message, numberOfTargets);
        for (String target : targets) {
            if (connector.wantToBlock(target, message)) {
                attackingCard.executeSpecialFunction(target, connector, gs);
                return null;
            }
        }
        return targets;
    }
}

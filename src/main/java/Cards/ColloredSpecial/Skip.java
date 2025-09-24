package Cards.ColloredSpecial;

import Connector.Connector;
import Enums.Color;
import Game.GameState;

public class Skip extends BaseColoredSpecial{

    public Skip(Color color) {
        super("Skip: " + color, 0, color);
    }

    @Override
    public void executeSpecialFunction(String executorName, Connector connector, GameState gs) {
        String target = connector.getPlayerTargets(executorName, "Skip", 1).getFirst();
        connector.addPlayerToSkip(target);
    }
}

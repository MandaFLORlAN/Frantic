package Cards.NormalAndCurses;

import Cards.InterfacesGroundclass.Card;
import Cards.InterfacesGroundclass.SpecialCard;
import Connector.Connector;
import Game.GameState;

public class BlackCard extends Card implements SpecialCard {
    public BlackCard(int number) {
        super("Black" + ": " + number, number, null);
    }

    @Override
    public boolean isPlayable(GameState gs) {
        return gs.getPlayableNumber() == this.getNumber();
    }

    @Override
    public void executeSpecialFunction(String executorName, Connector connector, GameState gs) {
        connector.triggerEvent(executorName);
    }
}


package Events.Basegame;

import Cards.InterfacesGroundclass.Card;
import Connector.Connector;
import Events.BaseEvent;
import Game.GameState;

import java.util.HashMap;
import java.util.Map;

public class GamblingMan extends BaseEvent {
    @Override
    public void executeEvent(Connector connector, String executor, GameState gameState) {
        connector.tellAllPlayers("Gambling Man: \n All Players give a Card. \n " +
                "The one with the second lowest number has to take all cards.");
        Map<String, Card> gamblingCards = new HashMap<String, Card>();
        for (String player : connector.getAllPlayerNames()) {
            gamblingCards.put(player, connector.getCardToGiveAway(player, 1).getFirst());
        }
    }
}

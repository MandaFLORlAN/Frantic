package Events.Basegame;

import Cards.InterfacesGroundclass.Card;
import Connector.Connector;
import Events.BaseEvent;
import Game.GameState;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GamblingMan extends BaseEvent {
    @Override
    public void executeEvent(Connector connector, String executor, GameState gameState) {
        connector.tellAllPlayers("Gambling Man: \n All Players give a Card. \n " +
                "The one with the second lowest number has to take all cards.");
        Map<String, List<Card>> gamblingCards = new HashMap<>();
        for (String player : connector.getAllPlayerNames()) {
            gamblingCards.put(player, connector.getCardToGiveAway(player, 1));
        }
        int smalestNumber = Integer.MAX_VALUE;
        int secondSmalestNumber = Integer.MAX_VALUE;
        String secondSmalestPlayerName = gamblingCards.keySet().stream().findFirst().get();
        for (String player : super.playersInOrders(connector, executor)) {
            Card currentCard = gamblingCards.get(player).getFirst();
            if (currentCard.getValue() < smalestNumber) {
                smalestNumber = currentCard.getValue();
            } else if (currentCard.getValue() >= smalestNumber &&
                    currentCard.getValue() < secondSmalestNumber) {
                secondSmalestNumber = currentCard.getValue();
                secondSmalestPlayerName = player;
            }
        }
        for (String player : gamblingCards.keySet()) {
            connector.transferCardFromPlayerToPlayer(gamblingCards.get(player), player, secondSmalestPlayerName);
        }
    }
}

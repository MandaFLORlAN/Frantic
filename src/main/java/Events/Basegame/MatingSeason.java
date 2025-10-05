package Events.Basegame;

import Cards.InterfacesGroundclass.Card;
import Connector.Connector;
import Events.BaseEvent;
import Game.GameState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatingSeason extends BaseEvent {
    @Override
    public void executeEvent(Connector connector, String executor, GameState gameState) {
        for (String player : connector.getAllPlayerNames()) {
            Map<Integer, List<Card>> numbers = new HashMap<>();
            for (Card card : connector.getAllCardsOfPlayer(player)) {
                if (numbers.containsKey(card.getNumber())) {
                    numbers.get(card.getNumber()).add(card);
                } else {
                    numbers.put(card.getNumber(), new ArrayList<>());
                    numbers.get(card.getNumber()).add(card);
                }
            }
            List<Card> cardsToDiscard = new ArrayList<>();
            for (Integer number : numbers.keySet()) {
                if (number == 0) continue;
                List<Card> cardsOfNumber = numbers.get(number);
                if (cardsOfNumber.size() > 1) {
                    cardsToDiscard.addAll(cardsOfNumber);
                }
            }
            connector.discardCards(player, cardsToDiscard);
        }
    }
}

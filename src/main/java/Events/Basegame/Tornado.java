package Events.Basegame;

import Cards.InterfacesGroundclass.Card;
import Connector.Connector;
import Events.BaseEvent;
import Game.GameState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tornado extends BaseEvent {
    @Override
    public void executeEvent(Connector connector, String executor, GameState gameState) {
        List<CardOrigin> allCards = new ArrayList<>();
        for (String playerName : connector.getAllPlayerNames()) {
            for (Card card : connector.getAllCardsOfPlayer(playerName)) {
                allCards.add(new CardOrigin(card, playerName));
            }
        }
        Random rand = new Random();
        while (!allCards.isEmpty()) {
            for (String playerName : super.playersInOrders(connector, executor)) {
                CardOrigin card = allCards.remove(rand.nextInt(allCards.size()));
                List<Card> cards = new ArrayList<>();
                cards.add(card.card);
                connector.transferCardFromPlayerToPlayer(cards, card.origin, playerName);
                if (allCards.isEmpty()) break;
            }
        }
    }

    private record CardOrigin(Card card, String origin) {}
}

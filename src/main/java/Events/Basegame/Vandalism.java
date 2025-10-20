package Events.Basegame;

import Cards.InterfacesGroundclass.Card;
import Connector.Connector;
import Enums.Color;
import Events.BaseEvent;
import Game.GameState;

import java.util.ArrayList;
import java.util.List;

public class Vandalism extends BaseEvent {
    @Override
    public void executeEvent(Connector connector, String executor, GameState gameState) {
        Color lastColor = connector.getLastColor();
        if (lastColor == null) return;
        for (String player : connector.getAllPlayerNames()) {
            List<Card> allCards = connector.getAllCardsOfPlayer(player);
            List<Card> colorCards = new ArrayList<>();
            for (Card card : allCards) {
                if (lastColor.equals(card.getColor())) {
                    colorCards.add(card);
                }
            }
            connector.discardCards(player, colorCards);
        }
    }
}

package Events.Basegame;

import Cards.InterfacesGroundclass.Card;
import Connector.Connector;
import Events.BaseEvent;
import Events.CardsToGiveAway;
import Game.GameState;

import java.util.*;

public class MerryChristmas extends BaseEvent {

    @Override
    public void executeEvent(Connector connector, String executor, GameState gameState) {
            List<String> players = connector.getAllPlayerNames();
            List<CardsToGiveAway> cardTransactions = new ArrayList<>();
            for (String player : players) {
                List<Card> cardsOfPlayer = connector.getAllCardsOfPlayer(player);
                List<String> targets = connector.getPlayerTargets(player,
                        "Merry christmas", cardsOfPlayer.size());
                for (int i = 0; i < targets.size(); i++) {
                    cardTransactions.add(new CardsToGiveAway(Collections.singletonList(cardsOfPlayer.get(i)),player, targets.get(i)));
                }
            }
            for (CardsToGiveAway cardFromTo : cardTransactions) {
                connector.transferCardFromPlayerToPlayer(
                        cardFromTo.cards(),
                        cardFromTo.giver(),
                        cardFromTo.reciever()
                );
            }
    }

}

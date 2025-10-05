package Events.Basegame;

import Cards.InterfacesGroundclass.Card;
import Connector.Connector;
import Events.BaseEvent;
import Events.CardsToGiveAway;
import Game.GameState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MerryChristmas extends BaseEvent {

    @Override
    public void executeEvent(Connector connector, String executor, GameState gameState) {
        List<CardsToGiveAway> cardsToGiveAway = new ArrayList<>();
        for (String playerName : connector.getAllPlayerNames()) {
            List<String> targets = connector.getPlayerTargets(playerName,
                    "Merry christmas",
                    gameState.getCards().get(playerName));
            Map<String, List<Card>> cardsToTarget = new HashMap<>();
            List<Card> cardsOfPlayer = connector.getAllCardsOfPlayer(playerName);
            for (int i = 0; i < targets.size(); i++) {
                String target = targets.get(i);
                if (!cardsToTarget.containsKey(target)) {
                    cardsToTarget.put(target, new ArrayList<>());
                }
                cardsToTarget.get(target).add(cardsOfPlayer.get(i));
            }
            for (String target : cardsToTarget.keySet()) {
                cardsToGiveAway.add(new CardsToGiveAway(
                        cardsToTarget.get(target),
                        playerName,
                        target
                ));
            }
        }
        for (CardsToGiveAway cards : cardsToGiveAway) {
            connector.transferCardFromPlayerToPlayer(
                    cards.cards(),
                    cards.giver(),
                    cards.reciever()
            );
        }
    }
}

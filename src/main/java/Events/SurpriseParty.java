package Events;

import Cards.InterfacesGroundclass.Card;
import Connector.Connector;
import Game.GameState;

import java.util.List;

public class SurpriseParty extends BaseEvent {
    @Override
    public void executeEvent(Connector connector, String executor, GameState gameState) {
        List<String> players = connector.getAllPlayerNames();
        for (String player : players) {
            String target = connector.getPlayerTargets(player, "SurpriseParty", 1).getFirst();
            List<Card> card = connector.getCardToGiveAway(player, 1);
            connector.transferCardFromPlayerToPlayer(card, player, target);
        }
    }
}

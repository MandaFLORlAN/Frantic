package Events.Basegame;

import Cards.InterfacesGroundclass.Card;
import Connector.Connector;
import Events.BaseEvent;
import Events.CardsToGiveAway;
import Game.GameState;

import java.util.ArrayList;
import java.util.List;

public class SurpriseParty extends BaseEvent {
    @Override
    public void executeEvent(Connector connector, String executor, GameState gameState) {
        List<String> players = connector.getAllPlayerNames();
        List<CardsToGiveAway> giveAwayTriples = new ArrayList<>();
        for (String player : players) {
            String target = connector.getPlayerTargets(player, "SurpriseParty", 1).getFirst();
            List<Card> cards = connector.getCardToGiveAway(player, 1);
            giveAwayTriples.add(new CardsToGiveAway(cards, player, target));
        }
        for (CardsToGiveAway triples : giveAwayTriples) {
            connector.transferCardFromPlayerToPlayer(triples.cards(), triples.giver(), triples.reciever());
        }
    }
}

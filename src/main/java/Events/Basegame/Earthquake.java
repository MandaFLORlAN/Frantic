package Events.Basegame;

import Connector.Connector;
import Events.BaseEvent;
import Events.CardsToGiveAway;
import Game.GameState;

import java.util.ArrayList;
import java.util.List;

public class Earthquake extends BaseEvent {

    @Override
    public void executeEvent(Connector connector, String executor, GameState gameState) {
        List<String> players = connector.getAllPlayerNames();
        List<CardsToGiveAway> pases = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            pases.add(new CardsToGiveAway(
                    connector.getAllCardsOfPlayer(players.get(i)).stream().toList(),
                    players.get(i),
                    players.get((i + 1) % players.size())
            ));
        }
        for (CardsToGiveAway cardsToGiveAway : pases) {
            connector.transferCardFromPlayerToPlayer(
                    cardsToGiveAway.cards(),
                    cardsToGiveAway.giver(),
                    cardsToGiveAway.reciever()
            );
        }
    }

}

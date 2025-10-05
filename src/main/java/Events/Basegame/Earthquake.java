package Events.Basegame;

import Cards.InterfacesGroundclass.Card;
import Connector.Connector;
import Events.BaseEvent;
import Game.GameState;

import java.util.ArrayList;
import java.util.List;

public class Earthquake extends BaseEvent {

    @Override
    public void executeEvent(Connector connector, String executor, GameState gameState) {
        List<String> players = connector.getAllPlayerNames();
        List<cardsToPasOn> pases = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            pases.add(new cardsToPasOn(
                    connector.getAllCardsOfPlayer(players.get(i)).stream().toList(),
                    players.get(i),
                    players.get((i + 1) % players.size())
            ));
        }
        for (cardsToPasOn cardsToPasOn : pases) {
            connector.transferCardFromPlayerToPlayer(
                    cardsToPasOn.cards,
                    cardsToPasOn.giver,
                    cardsToPasOn.reciever
            );
        }
    }

    private record cardsToPasOn(List<Card> cards, String giver, String reciever) {}
}

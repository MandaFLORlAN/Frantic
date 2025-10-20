package Events.Basegame;

import Cards.InterfacesGroundclass.Card;
import Connector.Connector;
import Events.BaseEvent;
import Game.GameState;

public class TheAllSeeingEye extends BaseEvent {

    @Override
    public void executeEvent(Connector connector, String executor, GameState gameState) {
        StringBuilder sb = new StringBuilder();
        for (String playerName : connector.getAllPlayerNames()) {
            sb.append(playerName).append(":\n");
            for (Card card : connector.getAllCardsOfPlayer(playerName)) {
                sb.append(card).append("\n");
            }
        }
        connector.tellAllPlayers(sb.toString());
    }
}

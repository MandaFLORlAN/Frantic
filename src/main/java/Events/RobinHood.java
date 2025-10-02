package Events;


import Connector.Connector;
import Game.GameState;

public class RobinHood extends BaseEvent
{
    public RobinHood() {}


    @Override
    public void executeEvent(Connector connector, String executor, GameState gameState) {
        String maxCardPlayer = super.playersWithMostCards(gameState).getFirst();
        String minCardPlayer = super.playersWithLeastCards(gameState).getFirst();
        //TODO finish logic
    }
}

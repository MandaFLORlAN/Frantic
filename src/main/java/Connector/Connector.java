package Connector;

import Cards.Card;
import ConsolePlayers.Player;
import ConsolePlayers.RandomBot;
import Enums.Color;
import Enums.FantasticOptions;
import Game.GameState;
import Statistics.StatisticsHandler;

import java.util.List;

public interface Connector {
    void startGame(List<Player> players, StatisticsHandler statistics) throws InterruptedException;

    //Methods used by Game
    boolean addCardToPlayer(String playerName, Card card);
    void itsTurn(String playerName);
    void winners(List<String> winnerNames);
    void updateGamestate(GameState gameState);

    //Methods used by Player
    boolean wantsToPlay(String playerName, String cardName);
    boolean wantsToGiveAway(String playerName, String cardName);

    //Methods used by Cards
    Color getPlayerColorWish(String playerName);
    void wishUpdate(String executorName, Color color);
    FantasticOptions getPlayerFantasticWish(String playerName);
    void wishUpdate(String executorName, FantasticOptions fantasticOptions);
    Player getPlayerTarget(String executorName, String message);
    Card getCardToGiveAway(String playerName);

}

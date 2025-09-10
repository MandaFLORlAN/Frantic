package Connector;

import Cards.Card;
import ConsolePlayers.Player;
import Enums.Color;
import Enums.FantasticOptions;
import Game.GameState;
import Statistics.StatisticsHandler;

import java.util.List;

public interface Connector {
    public void startGame(List<Player> players, StatisticsHandler statistics) throws InterruptedException;

    //method for game
    boolean addCardToPlayer(String playerName, Card card);
    void itsTurn(String playerName);
    void winners(List<String> winnerIds);
    void updateGamestate(GameState gameState);
    void wishUpdate(Player executor, Color color);
    void wishUpdate(Player executor, FantasticOptions fantasticOptions);

    //method for players
    boolean wantsToPlay(String playerName, Card card);
    void updateGame();

}

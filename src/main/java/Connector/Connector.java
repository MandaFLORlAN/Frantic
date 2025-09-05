package Connector;

import Cards.Card;
import ConsolePlayers.Player;
import Game.GameState;
import Statistics.StatisticsHandler;

import java.util.List;

public interface Connector {
    public void startGame(List<Player> players, StatisticsHandler statistics) throws InterruptedException;

    //method for game
    public boolean addCardToPlayer(int playerId, Card card);
    public void itsTurn(int playerId);
    public void winners(List<Integer> winnerIds);
    public void updateGamestate(GameState gameState);

    //method for players
    public boolean wantsToPlay(int playerId, Card card);
    public void updateGame();

}

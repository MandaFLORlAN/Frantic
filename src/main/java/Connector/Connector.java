package Connector;

import Cards.InterfacesGroundclass.Card;
import Players.Player;
import Enums.Color;
import Enums.FantasticOptions;
import Game.GameState;

import java.util.List;

public interface Connector {
    void startGame(List<Player> players) throws InterruptedException;

    //Methods used by Game
    boolean addCardToPlayer(String playerName, Card card);
    void itsTurn(String playerName);
    void winners(List<String> winnerNames);
    void updateGamestate(GameState gameState);

    //Methods used by Player
    boolean wantsToPlay(String playerName, String cardName);
    void executeSpecialFunction(String playerName, String cardName);
    //Methods used by SpecialCards
    Color getPlayerColorWish(String playerName);
    void wishUpdate(String executorName, Color color);
    FantasticOptions getPlayerFantasticWish(String playerName);
    void wishUpdate(String executorName, FantasticOptions fantasticOptions);
    List<String> getPlayerTargets(String executorName, String message, int numberOfTargets);
    List<Card> getCardToGiveAway(String playerName, int numberOfCards);
    List<Card> drawRandomCardFromPlayer(String playerName, int numberOfCards);
    void makePlayerDraw(String playerName, String message, int numberOfCards);
    void transferCardFromPlayerToPlayer(List<Card> cards, String giverName, String recieverName);
    boolean checkIfPlayerWantEffect(String playerName);
}

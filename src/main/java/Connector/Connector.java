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
    void tellAllPlayers(String message);
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
    void addPlayerToSkip(String playerName);
    void triggerEvent(String executor);
    boolean wantToBlock(String player, String attackCard);
    void niceTry(List<String> alemostWinners, String executor);
    List<String> getChosenCardsFromPlayer(String player, List<Card> cards, int numberOfCards);
    //Methods for Events
    List<String> getAllPlayerNames();
    List<Card> getAllCardsOfPlayer(String playerName);
    void discardCards(String playerName, List<Card> cards);
    List<Card> drawRandomCards(int numberOfCards);
    Color getLastColor();
}

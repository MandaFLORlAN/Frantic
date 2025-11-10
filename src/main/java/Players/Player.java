package Players;

import Cards.InterfacesGroundclass.Card;

import java.util.List;

public interface Player {
    //basic Gameloop
    void updateGamestate(String gameState);
    void updateGameActions(String Message);
    void addCard(String cardName);
    void addCard(String cardName, String message);
    void removeCard(String cardName);
    void playMove();
    void clearCards();
    String getPlayerName();
    List<Card> getCards();
    //for special Cards
    boolean wantToUseEffect();
    String fantasticWish();
    String wishColor();
    List<String> getTargets(String message, int numberOfTargets);
    List<String> getCardsToGiveAway(int numberOfCards);
    String drawRandomCard();
    boolean wantToBlock(String attackCard);
    List<String> choseCards(List<String> cards, int numberOfCards);
}

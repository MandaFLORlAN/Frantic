package Players;

import Cards.InterfacesGroundclass.Card;
import Game.GameState;

import java.util.List;

public interface Player {
    //basic Gameloop
    void updateGamestate(GameState gameState);
    void updateGameActions(String Message);
    void addCard(Card card);
    void addCard(Card card, String message);
    void removeCard(Card cardName);
    void playMove();
    void clearCards();
    String getPlayerName();
    List<Card> getCards();
    //for special Cards
    boolean wantToUseEffect();
    String fantasticWish();
    String wishColor();
    List<String> getTargets(String message, int numberOfTargets);
    List<Card> getCardsToGiveAway(int numberOfCards);
    Card drawRandomCard();
    boolean wantToBlock(String attackCard);
    List<Card> choseCards(List<Card> cards, int numberOfCards);
}

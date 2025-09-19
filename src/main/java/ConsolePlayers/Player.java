package ConsolePlayers;

import java.util.List;

public interface Player {
    //basic Gameloop
    void updateGamestate(String gameState);
    void updateGameActions(String Message);
    void addCard(String cardName);
    void addCard(String cardName, String message);
    void removeCard(String cardName);
    void playMove();
    void newRound();
    String getPlayerName();

    //for special Cards
    String fantasticWish();
    String wishColor();
    String getTarget(String message);
    List<String> getCardsToGiveAway(int numberOfCards);
}

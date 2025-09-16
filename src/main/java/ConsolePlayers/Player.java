package ConsolePlayers;

import Enums.FantasticOptions;

public interface Player {
    //basic Gameloop
    void updateGamestate(String gameState);
    void updateGameActions(String Message);
    void addCard(String cardName);
    void playMove();
    void newRound();
    String getPlayerName();

    //for special Cards
    String fantasticWish();
    String wishColor();
    String getTarget(String message);

}

package ConsolePlayers;

import Cards.Card;
import Enums.FantasticOptions;
import Game.GameState;

public interface Player {
    void updateGamestate(GameState gameState);
    void updateGameActions(String Message);
    void addCard(Card card);
    void playMove();
    FantasticOptions fantasticWish();
    Player getTarget(String card);
    String getPlayerName();
    public void newRound();
}

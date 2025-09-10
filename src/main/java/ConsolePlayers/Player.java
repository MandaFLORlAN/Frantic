package ConsolePlayers;

import Cards.Card;
import Connector.Connector;
import Enums.FantasticOptions;
import Game.GameState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    protected String playerName;
    protected List<Card> cards = new ArrayList<>();
    protected Connector connector;
    protected GameState gameState;

    public Player(String playerName, Connector connector) {
        this.playerName = playerName;
        this.connector = connector;
    }


    public void updateGamestate(GameState gameState) {
        this.gameState = gameState;
    }

    public void updateGameActions(String message) {

    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void playMove() {
        for (Card card : cards) {
            if (card.isPlayable(this.gameState)) {
                if (connector.wantsToPlay(this.playerName, card)) {
                    this.cards.remove(card);
                    return;
                } else {
                    connector.wantsToPlay(this.playerName, null);
                    return;
                }
            }
        }
        connector.wantsToPlay(this.playerName, null);
    }

    public FantasticOptions fantasticWish() {
        return FantasticOptions.values()[new Random().nextInt(FantasticOptions.values().length)];
    }

    public Player getTarget(String card) {
        return null;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void newRound() {
        this.cards.clear();
    }

    @Override
    public String toString() {
        return this.playerName;
    }
}

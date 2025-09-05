package ConsolePlayers;

import Cards.Card;
import Connector.Connector;
import Game.GameState;

import java.util.ArrayList;
import java.util.List;

public class Player {
    protected int playerId;
    protected String playerName;
    protected List<Card> cards = new ArrayList<>();
    protected Connector connector;
    protected GameState gameState;

    public Player(String playerName, Connector connector) {
        this.playerName = playerName;
        this.connector = connector;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
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
                if (connector.wantsToPlay(this.playerId, card)) {
                    this.cards.remove(card);
                    return;
                } else {
                    connector.wantsToPlay(this.playerId, null);
                    return;
                }
            }
        }
        connector.wantsToPlay(this.playerId, null);
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void newRound() {
        this.cards.clear();
    }

    @Override
    public String toString() {
        return this.playerName;
    }
}

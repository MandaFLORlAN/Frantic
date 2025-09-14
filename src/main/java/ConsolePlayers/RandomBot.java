package ConsolePlayers;

import Cards.Card;
import Connector.Connector;
import Enums.FantasticOptions;
import Game.GameState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomBot implements Player{
    protected String playerName;
    protected List<Card> cards = new ArrayList<>();
    protected Connector connector;
    protected GameState gameState;

    public RandomBot(String playerName, Connector connector) {
        this.playerName = playerName;
        this.connector = connector;
    }

    @Override
    public void updateGamestate(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void updateGameActions(String message) {

    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
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

    @Override
    public FantasticOptions fantasticWish() {
        return FantasticOptions.values()[new Random().nextInt(FantasticOptions.values().length)];
    }

    @Override
    public RandomBot getTarget(String card) {
        return null;
    }

    @Override
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public void newRound() {
        this.cards.clear();
    }

    @Override
    public String toString() {
        return this.playerName;
    }
}

package ConsolePlayers;

import Cards.Card;
import Connector.Connector;
import Enums.Color;
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
    public void updateGamestate(String gameState) {
        this.gameState = GameState.fromString(gameState);
    }

    @Override
    public void updateGameActions(String message) {
        //ignore for bots
    }

    @Override
    public void addCard(String cardName) {
        this.cards.add(Card.fromString(cardName));
    }

    @Override
    public void playMove() {
        for (Card card : cards) {
            if (card.isPlayable(this.gameState)) {
                if (connector.wantsToPlay(this.playerName, card.toString())) {
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
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public String fantasticWish() {
        return FantasticOptions.values()[new Random().nextInt(FantasticOptions.values().length)].toString();
    }

    @Override
    public String wishColor() {
        return Color.values()[new Random().nextInt(Color.values().length)].toString();
    }

    @Override
    public String getTarget(String message) {
        //TODO später
        return "";
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

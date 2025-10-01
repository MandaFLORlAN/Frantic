package Players;

import Cards.InterfacesGroundclass.Card;
import Cards.Wishcards.Counterattack;
import Connector.Connector;
import Enums.Color;
import Enums.FantasticOptions;
import Game.GameState;
import org.w3c.dom.css.Counter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    public void addCard(String cardName, String message) {
        this.addCard(cardName);
    }

    @Override
    public void removeCard(String cardName) {
        if (this.cards.contains(Card.fromString(cardName))) {
            this.cards.remove(Card.fromString(cardName));
        } else {
            System.out.println("Card not found: " + cardName);
        }
    }

    @Override
    public void playMove() {
        for (Card card : cards) {
            if (card.isPlayable(this.gameState, this.playerName)) {
                if (connector.wantsToPlay(this.playerName, card.toString())) {
                    this.cards.remove(card);
                    connector.executeSpecialFunction(this.playerName, card.toString());
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
    public List<Card> getCards() {
        return this.cards;
    }

    @Override
    public boolean wantToUseEffect() {
        return true;
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
    public List<String> getTargets(String message, int numberOfTargets) {
        List<String> targets = new ArrayList<>();
        List<String> players = new ArrayList<>(this.gameState.getCards().keySet());
        Random r = new Random();
        for (int i = 0; i< numberOfTargets; i++) {
            String target = players.get(r.nextInt(players.size()));
            if (Objects.equals(target, this.playerName)) i--;
            else targets.add(target);
        }
        return targets;
    }

    @Override
    public List<String> getCardsToGiveAway(int numberOfCards) {
        List<String> cardsToGiveAway = new ArrayList<>();
        if (this.cards.size()<numberOfCards) numberOfCards = this.cards.size();
        for (int i = 0; i <  numberOfCards; i++) {
            cardsToGiveAway.add(this.cards.remove(new Random().nextInt(this.cards.size())).toString());
        }
        //cards will be taken in the transfer card method, they are remooved to not be picked twice
        for (String card : cardsToGiveAway) {
            this.cards.add(Card.fromString(card));
        }
        return cardsToGiveAway;
    }

    @Override
    public String drawRandomCard() {
        if (this.cards.isEmpty()) {
            return null;
        }
        return this.cards.get(new Random().nextInt(this.cards.size())).toString();
    }

    @Override
    public boolean wantToBlock(String attackCard) {
        if (this.cards.contains(new Counterattack())) {
            this.cards.remove(new Counterattack());
            return true;
        } else {
            return false;
        }
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

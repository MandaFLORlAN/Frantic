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
    public void updateGamestate(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void updateGameActions(String message) {
        //ignore for bots
    }

    @Override
    public void addCard(Card card) {
        this.cards.add(card);
    }

    @Override
    public void addCard(Card card, String message) {
        this.addCard(card);
    }

    @Override
    public void removeCard(Card card) {
        if (!this.cards.remove(card)) {
            System.out.println("Card not found: " + card.getName());
        }
    }

    @Override
    public void playMove() {
        List<Card> playableCards = new ArrayList<>();
        for (Card card : cards) {
            if (card.isPlayable(this.gameState, this.playerName)) {
                playableCards.add(card);
            }
        }
        if (playableCards.isEmpty()) {
            connector.wantsToPlay(this.playerName, null);
            return;
        }
        Card card = playableCards.get(new Random().nextInt(playableCards.size()));
        if (connector.wantsToPlay(this.playerName, card)) {
            this.cards.remove(card);
            connector.executeSpecialFunction(this.playerName, card);
        } else {
            connector.wantsToPlay(this.playerName, null);
        }
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
        players.remove(this.playerName);
        Random r = new Random();
        for (int i = 0; i< numberOfTargets; i++) {
            targets.add(players.get(r.nextInt(players.size())));
        }
        players.add(playerName);
        return targets;
    }

    @Override
    public List<Card> getCardsToGiveAway(int numberOfCards) {
        List<Card> cardsToGiveAway = new ArrayList<>();
        if (this.cards.size()<numberOfCards) numberOfCards = this.cards.size();
        for (int i = 0; i <  numberOfCards; i++) {
            cardsToGiveAway.add(this.cards.remove(new Random().nextInt(this.cards.size())));
        }
        //cards will be taken in the transfer card method, they are removed to not be picked twice
        for (Card card : cardsToGiveAway) {
            this.cards.add(card);
        }
        return cardsToGiveAway;
    }

    @Override
    public Card drawRandomCard() {
        if (this.cards.isEmpty()) {
            return null;
        }
        return this.cards.get(new Random().nextInt(this.cards.size()));
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
    public List<Card> choseCards(List<Card> cards, int numberOfCards) {
        Random r = new Random();
        List<Card> chosenCards = new ArrayList<>();
        for (int i  = 0; i < numberOfCards; i++) {
            chosenCards.add(cards.get(r.nextInt(cards.size())));
        }
        return chosenCards;
    }

    @Override
    public void clearCards() {
        this.cards.clear();
    }

    @Override
    public String toString() {
        return this.playerName;
    }
}

package Game;

import Cards.ColloredSpecial.SecondChance;
import Cards.InterfacesGroundclass.BasicWishcard;
import Cards.InterfacesGroundclass.Card;
import Cards.InterfacesGroundclass.WishCard;
import Cards.NormalAndCurses.BasicCurse;
import Cards.NormalAndCurses.FuckYou;
import Cards.Wishcards.Fantastic;
import Cards.Wishcards.NiceTry;
import Connector.Connector;
import Connector.PointBasedConnector;
import Events.Event;
import Enums.Color;
import Enums.FantasticOptions;
import Repository.CardDatabase;

import java.util.*;

public class Game {
    protected List<Card> unusedCards;
    protected List<Event> unusedEvents;
    protected List<Card> playedCards;
    protected List<Event> playedEvents;
    protected Card lastPlayedCard;
    protected GameState gameState;
    protected final Connector connector;
    protected final int startCards;
    protected int movesPlayed = 0;
    protected boolean gameOver = false;
    protected final Map<String, List<Card>> players;
    protected int startOffset = 0;
    protected List<String> playersToSkip = new ArrayList<>();
    protected final boolean pointBased;
    protected boolean TESTCASE = false;

    public Game(List<String> names, Connector connector, int startCards) {
        this.connector = connector;
        this.startCards = startCards;
        this.pointBased = connector instanceof PointBasedConnector;
        this.players = new HashMap<>();
        for (String name : names) {
            players.put(name, new ArrayList<>());
        }
        resetGame();
    }

    public Game(List<String> names, Connector connector) {
        this.TESTCASE = true;
        this.connector = connector;
        this.startCards = 0;
        this.pointBased = connector instanceof PointBasedConnector;
        this.players = new HashMap<>();
        for (String name : names) {
            players.put(name, new ArrayList<>());
        }
        resetGame();
    }

    public void startGame() {
        resetGame();
        while (this.lastPlayedCard == null || this.lastPlayedCard instanceof BasicCurse || this.lastPlayedCard instanceof BasicWishcard) {
            resetGame();
            this.lastPlayedCard = drawCard();
        }
        for (int i = 0; i < startCards; i++) {
            for (String p : players.keySet()) {
                Card card = drawCard();
                connector.addCardToPlayer(p, card);
                this.players.get(p).add(card);
            }
        }
        updateGameState();
        gameLoop();
    }

    protected void resetGame() {
        if (TESTCASE) {
            this.unusedCards = CardDatabase.getAllCards();
            this.unusedEvents = CardDatabase.getAllEvents();
        } else {
            this.unusedCards = CardDatabase.getShuffledCards();
            if (pointBased) {
                this.unusedEvents = CardDatabase.getShuffledEvents();
            } else  {
                this.unusedEvents = CardDatabase.getShuffledEventsNoPoints();
            }
        }
        this.playedCards = new ArrayList<>();
        this.playedEvents = new ArrayList<>();
        this.gameState = new GameState();
        this.playersToSkip = new ArrayList<>();
        for (String name : players.keySet()) {
            this.players.put(name, new ArrayList<>());
        }
        this.movesPlayed = 0;
        this.gameOver = false;
    }

    private void gameLoop() {
        while (!gameOver) {
            String nextPlayer = players.keySet().stream().toList().get((movesPlayed + startOffset) % players.size());
            if (playersToSkip.contains(nextPlayer)) {
                playersToSkip.remove(nextPlayer);
                connector.tellAllPlayers(nextPlayer + " has been skipped");
                startOffset++;
            } else {
                connector.itsTurn(nextPlayer);
                movesPlayed++;
                checkGameOver();
            }
        }
    }

    public void checkGameOver() {
        List<String> winners = new ArrayList<>();
        for (String playerName : players.keySet()) {
            if (players.get(playerName).isEmpty()) {
                winners.add(playerName);
            }
        }
        if (!winners.isEmpty()) {
            for (String player : players.keySet()) {
                if (players.get(player).contains(new NiceTry())) {
                    connector.niceTry(winners, player);
                    return;
                }
            }
            this.gameOver = true;
            connector.winners(winners);
        }
    }

    public boolean canPlay(String playerName, Card card) {
        if (card == null) {
            Card newCard = drawCard();
            connector.addCardToPlayer(playerName, newCard);
            players.get(playerName).add(newCard);
            updateGameState();
            return true;
        }
        List<Card> cards = players.get(playerName);
        boolean cardExists = false;
        for (Card c : cards) {
            if (c.equals(card)) {
                cardExists = true;
                cards.remove(c);
                if (card instanceof SecondChance) {
                    this.startOffset--;
                }
                break;
            }
        }
        if (!cardExists) {
            return false;
        }
        if (card instanceof FuckYou) {
            addCardBelowTopCard(card);
        } else {
            addCardOnPile(card);
        }
        return true;
    }

    public void addCardOnPile(Card card) {
        this.lastPlayedCard = card;
        this.playedCards.add(card);
        updateGameState();
    }

    public void addCardBelowTopCard(Card card) {
        Card ram = this.playedCards.removeLast();
        this.playedCards.add(card);
        this.playedCards.add(ram);
        updateGameState();
    }

    public void updateWish(FantasticOptions fantasticOptions) {
        if (this.lastPlayedCard instanceof Fantastic) {
            switch (fantasticOptions) {
                case ONE:
                    ((Fantastic) this.lastPlayedCard).setNumber(1);
                    ((Fantastic) this.lastPlayedCard).setName(this.lastPlayedCard.getName() + ": 1");
                    break;
                case TWO:
                    ((Fantastic) this.lastPlayedCard).setNumber(2);
                    ((Fantastic) this.lastPlayedCard).setName(this.lastPlayedCard.getName() + ": 2");
                    break;
                case THREE:
                    ((Fantastic) this.lastPlayedCard).setNumber(3);
                    ((Fantastic) this.lastPlayedCard).setName(this.lastPlayedCard.getName() + ": 3");
                    break;
                case FOUR:
                    ((Fantastic) this.lastPlayedCard).setNumber(4);
                    ((Fantastic) this.lastPlayedCard).setName(this.lastPlayedCard.getName() + ": 4");
                    break;
                case FIVE:
                    ((Fantastic) this.lastPlayedCard).setNumber(5);
                    ((Fantastic) this.lastPlayedCard).setName(this.lastPlayedCard.getName() + ": 5");
                    break;
                case SIX:
                    ((Fantastic) this.lastPlayedCard).setNumber(6);
                    ((Fantastic) this.lastPlayedCard).setName(this.lastPlayedCard.getName() + ": 6");
                    break;
                case SEVEN:
                    ((Fantastic) this.lastPlayedCard).setNumber(7);
                    ((Fantastic) this.lastPlayedCard).setName(this.lastPlayedCard.getName() + ": 7");
                    break;
                case EIGHT:
                    ((Fantastic) this.lastPlayedCard).setNumber(8);
                    ((Fantastic) this.lastPlayedCard).setName(this.lastPlayedCard.getName() + ": 8");
                    break;
                case NINE:
                    ((Fantastic) this.lastPlayedCard).setNumber(9);
                    ((Fantastic) this.lastPlayedCard).setName(this.lastPlayedCard.getName() + ": 9");
                    break;
                case TEN:
                    ((Fantastic) this.lastPlayedCard).setNumber(10);
                    ((Fantastic) this.lastPlayedCard).setName(this.lastPlayedCard.getName() + ": 10");
                    break;
                case BLUE:
                    ((Fantastic) this.lastPlayedCard).setColor(Color.BLUE);
                    ((Fantastic) this.lastPlayedCard).setName(this.lastPlayedCard.getName() + ": Blue");
                    break;
                case RED:
                    ((Fantastic) this.lastPlayedCard).setColor(Color.RED);
                    ((Fantastic) this.lastPlayedCard).setName(this.lastPlayedCard.getName() + ": Red");
                    break;
                case GREEN:
                    ((Fantastic) this.lastPlayedCard).setColor(Color.GREEN);
                    ((Fantastic) this.lastPlayedCard).setName(this.lastPlayedCard.getName() + ": Green");
                    break;
                case YELLOW:
                    ((Fantastic) this.lastPlayedCard).setColor(Color.YELLOW);
                    ((Fantastic) this.lastPlayedCard).setName(this.lastPlayedCard.getName() + ": Yellow");
                    break;
                case PURPLE:
                    ((Fantastic) this.lastPlayedCard).setColor(Color.PURPLE);
                    ((Fantastic) this.lastPlayedCard).setName(this.lastPlayedCard.getName() + ": Purple");
                    break;
            }
            updateGameState();
        }
    }

    public void updateWish(Color color) {
        if (this.lastPlayedCard instanceof WishCard) {
            switch (color) {
                case BLUE:
                    ((WishCard) this.lastPlayedCard).setColor(Color.BLUE);
                    ((WishCard) this.lastPlayedCard).setName(this.lastPlayedCard.getName() + ": Blue");
                    break;
                case RED:
                    ((WishCard) this.lastPlayedCard).setColor(Color.RED);
                    ((WishCard) this.lastPlayedCard).setName(this.lastPlayedCard.getName() + ": Red");
                    break;
                case GREEN:
                    ((WishCard) this.lastPlayedCard).setColor(Color.GREEN);
                    ((WishCard) this.lastPlayedCard).setName(this.lastPlayedCard.getName() + ": Green");
                    break;
                case YELLOW:
                    ((WishCard) this.lastPlayedCard).setColor(Color.YELLOW);
                    ((WishCard) this.lastPlayedCard).setName(this.lastPlayedCard.getName() + ": Yellow");
                    break;
                case PURPLE:
                    ((WishCard) this.lastPlayedCard).setColor(Color.PURPLE);
                    ((WishCard) this.lastPlayedCard).setName(this.lastPlayedCard.getName() + ": Purple");
                    break;
            }
            updateGameState();
        }
    }

    public void transferCardFromTo(Card card, String giverName, String receiverName) {
        this.players.get(giverName).remove(card);
        this.players.get(receiverName).add(card);
    }

    private void updateGameState() {
        this.gameState.setPlayableColor(this.lastPlayedCard.getColor());
        this.gameState.setPlayableNumber(this.lastPlayedCard.getNumber());
        this.gameState.setLastCardName(this.lastPlayedCard.getName());
        this.gameState.setCards(new HashMap<>());
        for (String playerName : this.players.keySet()) {
            this.gameState.getCards().put(playerName, this.players.get(playerName).size());
        }
        connector.updateGamestate(this.gameState);
    }

    public Card drawCard() {
        if (unusedCards.isEmpty()) {
            if (TESTCASE) unusedCards = CardDatabase.getAllCards();
            else unusedCards = CardDatabase.getShuffledCards();
        }
        return unusedCards.removeFirst();
    }

    public void addCardToPlayer(String playerName, Card card) {
        this.players.get(playerName).add(card);
    }

    public void addPlayerToSkip(String playerName) {
        this.playersToSkip.add(playerName);
    }

    public GameState getGameState() {
        return gameState;
    }

    public Event getNextEvent() {
        if (unusedEvents.isEmpty()) unusedEvents = CardDatabase.getShuffledEvents();
        Event nextEvent = this.unusedEvents.removeFirst();
        this.playedEvents.add(nextEvent);
        return nextEvent;

    }

    public void cardThrownIn(String playerName, Card card) {
        this.players.get(playerName).remove(card);
        this.playedCards.add(card);
    }

    public Color getLastColor() {
        int playedCardsSize = this.playedCards.size();
        for (int i = 1; i <= playedCardsSize; i++) {
            if (this.playedCards.get(playedCardsSize - i).getColor() != null) {
                return  this.playedCards.get(playedCardsSize - i).getColor();
            }
        }
        return null;
    }

    public int getStartOffset() {
        return startOffset;
    }

    public List<String> getPlayersToSkip() {
        return playersToSkip;
    }
}

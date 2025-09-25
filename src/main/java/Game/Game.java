package Game;

import Cards.ColloredSpecial.SecondChance;
import Cards.InterfacesGroundclass.Card;
import Cards.InterfacesGroundclass.WishCard;
import Cards.Wishcards.Fantastic;
import Connector.Connector;
import Events.Event;
import Players.Player;
import Enums.Color;
import Enums.FantasticOptions;
import Repository.CardDatabase;

import java.util.*;

public class Game {
    private List<Card> unusedCards;
    private List<Event> unusedEvents;
    private List<Card> playedCards;
    private List<Event> playedEvents;
    private Card lastPlayedCard;
    private String lastPlayerName;
    private GameState gameState;
    private Connector connector;
    private int startCards;
    private int movesPlayed = 0;
    private boolean gameOver = false;
    private Map<String, List<Card>> players;
    private int startOffset = 0;
    private List<String> playersToSkip = new ArrayList<>();

    public Game(List<String> names, Connector connector, int startCards) {
        this.connector = connector;
        this.startCards = startCards;
        this.gameState = new GameState();
        this.players = new HashMap<>();
        for (String name : names) {
            players.put(name, new ArrayList<>());
        }
    }

    public void startGame() throws InterruptedException {
        resetGame();
        for (int i = 0; i < startCards; i++) {
            for (String p : players.keySet()) {
                Card card = drawCard();
                connector.addCardToPlayer(p, card);
                this.players.get(p).add(card);
            }
        }
        this.lastPlayedCard = drawCard();
        this.lastPlayerName = "TODO";
        updateGamestate();
        gameLoop();
    }

    private void resetGame() {
        this.unusedCards = CardDatabase.getShuffledCards();
        this.unusedEvents = CardDatabase.getShuffledEvents();
        this.playedCards = new ArrayList<>();
        this.playedEvents = new ArrayList<>();
        this.gameState = new GameState();
        this.playersToSkip = new ArrayList<>();
        for (String name : players.keySet()) {
            this.players.get(name).clear();
            this.players.put(name, new ArrayList<>());
        }
        this.movesPlayed = 0;
        this.startOffset = new Random().nextInt(players.size());
        this.gameOver = false;
    }

    private void gameLoop() throws InterruptedException {
        while (!gameOver) {
            String nextPlayer = players.keySet().stream().toList().get((movesPlayed + startOffset) % players.size());
            if (playersToSkip.contains(nextPlayer)) {
                playersToSkip.remove(nextPlayer);
                connector.tellAllPlayers(nextPlayer + " has been skiped");
                startOffset++;
            } else {
                connector.itsTurn(nextPlayer);
                movesPlayed++;
                /*TimeUnit.SECONDS.sleep(1);*/
                checkGameOver();
            }
        }
    }

    private void checkGameOver() {
        List<String> winners = new ArrayList<>();
        for (String playerName : players.keySet()) {
            if (players.get(playerName).isEmpty()) {
                winners.add(playerName);
            }
        }
        if (!winners.isEmpty()) {
            this.gameOver = true;
            connector.winners(winners);
        }
    }

    public boolean canPlay(String playerName, Card card) {
        if (card == null) {
            Card newCard = drawCard();
            connector.addCardToPlayer(playerName, newCard);
            players.get(playerName).add(newCard);
            updateGamestate();
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
        this.lastPlayedCard = card;
        this.playedCards.add(card);
        this.lastPlayerName = playerName;
        updateGamestate();
        return true;
    }

    public void updateWish(Player player, FantasticOptions fantasticOptions) {
        if (!Objects.equals(lastPlayerName, player.getPlayerName())) return;
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
            updateGamestate();
        }
    }

    public void updateWish(Player player, Color color) {
        if (!Objects.equals(lastPlayerName, player.getPlayerName())) return;
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
            updateGamestate();
        }
    }

    public void transferCardFromTo(Card card, String giverName, String recieverName) {
        this.players.get(giverName).remove(card);
        this.players.get(recieverName).add(card);
    }

    private void updateGamestate() {
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
        if (!unusedCards.isEmpty()) {
            unusedCards = CardDatabase.getShuffledCards();
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
        Event nextEvent = this.unusedEvents.removeFirst();
        this.playedEvents.add(nextEvent);
        return nextEvent;

    }
}

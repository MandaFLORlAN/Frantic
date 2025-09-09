package Game;

import Cards.Card;
import Cards.Fantastic;
import Connector.Connector;
import ConsolePlayers.Player;
import Enums.Color;
import Enums.FantasticOptions;
import Repository.CardDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Game{
    private List<Card> drawingPile;
    private List<Card> pile;
    private Card lastPlayedCard;
    private int lastPlayerId;
    private GameState gameState;
    private int numberOfPlayers;
    private Connector connector;
    private int startCards;
    private int movesPlayed = 0;
    private boolean gameOver = false;
    private List<List<Card>> players;
    private int startPlayer = 0;

    public Game(int numberOfPlayers, Connector connector, int startCards) {
        this.numberOfPlayers = numberOfPlayers;
        this.connector = connector;
        this.startCards = startCards;
        this.drawingPile = CardDatabase.getShuffledCards();
        this.pile = new ArrayList<>();
        this.gameState = new GameState();
    }

    public void startGame() throws InterruptedException {
        resetGame();
        for (int i = 0; i < startCards; i++){
            for (int p = 0; p < numberOfPlayers; p++){
                Card card = drawCard();
                connector.addCardToPlayer(p, card);
                this.players.get(p).add(card);
            }
        }
        this.lastPlayedCard = drawCard();
        this.lastPlayerId = this.startPlayer;
        updateGamestate();
        gameLoop();
    }

    private void resetGame() {
        this.drawingPile = CardDatabase.getShuffledCards();
        this.pile = new ArrayList<>();
        this.players = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++){
            players.add(new ArrayList<>());
        }
        this.movesPlayed = 0;
        this.startPlayer = new Random().nextInt(numberOfPlayers);
        this.gameOver = false;
    }

    private void gameLoop() throws InterruptedException {
        while (!gameOver) {
            connector.itsTurn((movesPlayed + startPlayer) % numberOfPlayers );
            movesPlayed++;
            /*TimeUnit.SECONDS.sleep(1);*/
            checkGameOver();
        }
    }

    private void checkGameOver() {
        List<Integer> winners = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++){
            if (players.get(i).isEmpty()){
                winners.add(i);
            }
        }
        if(!winners.isEmpty()){
            this.gameOver = true;
            connector.winners(winners);
        }
    }

    public boolean canPlay(int playerId, Card card) {
        if (card == null){
            Card newCard = drawCard();
            connector.addCardToPlayer(playerId, newCard);
            players.get(playerId).add(newCard);
            updateGamestate();
            return true;
        }
        List<Card> cards = players.get(playerId);
        boolean cardExists = false;
        for (Card c : cards){
            if (c.equals(card)){
                cardExists = true;
                cards.remove(c);
                break;
            }
        }
        if (!cardExists){
            return false;
        }
        this.lastPlayedCard = card;
        updateGamestate();
        return true;
    }

    public void updateWish(Player player, FantasticOptions fantasticOptions) {
        if(lastPlayerId!=player.getPlayerId()) return;
        if (this.lastPlayedCard instanceof Fantastic) {
            switch (fantasticOptions) {
                case ONE:
                    ((Fantastic) this.lastPlayedCard).setNumber(1);
                    ((Fantastic) this.lastPlayedCard).setName("Fantastic: 1");
                    break;
                case TWO:
                    ((Fantastic) this.lastPlayedCard).setNumber(2);
                    ((Fantastic) this.lastPlayedCard).setName("Fantastic: 2");
                    break;
                case THREE:
                    ((Fantastic) this.lastPlayedCard).setNumber(3);
                    ((Fantastic) this.lastPlayedCard).setName("Fantastic: 3");
                    break;
                case FOUR:
                    ((Fantastic) this.lastPlayedCard).setNumber(4);
                    ((Fantastic) this.lastPlayedCard).setName("Fantastic: 4");
                    break;
                case FIVE:
                    ((Fantastic) this.lastPlayedCard).setNumber(5);
                    ((Fantastic) this.lastPlayedCard).setName("Fantastic: 5");
                    break;
                case SIX:
                    ((Fantastic) this.lastPlayedCard).setNumber(6);
                    ((Fantastic) this.lastPlayedCard).setName("Fantastic: 6");
                    break;
                case SEVEN:
                    ((Fantastic) this.lastPlayedCard).setNumber(7);
                    ((Fantastic) this.lastPlayedCard).setName("Fantastic: 7");
                    break;
                case EIGHT:
                    ((Fantastic) this.lastPlayedCard).setNumber(8);
                    ((Fantastic) this.lastPlayedCard).setName("Fantastic: 8");
                    break;
                case NINE:
                    ((Fantastic) this.lastPlayedCard).setNumber(9);
                    ((Fantastic) this.lastPlayedCard).setName("Fantastic: 9");
                    break;
                case TEN:
                    ((Fantastic) this.lastPlayedCard).setNumber(10);
                    ((Fantastic) this.lastPlayedCard).setName("Fantastic: 10");
                    break;
                case BLUE:
                    ((Fantastic) this.lastPlayedCard).setColor(Color.BLUE);
                    ((Fantastic) this.lastPlayedCard).setName("Fantastic: Blue");
                    break;
                case RED:
                    ((Fantastic) this.lastPlayedCard).setColor(Color.RED);
                    ((Fantastic) this.lastPlayedCard).setName("Fantastic: Red");
                    break;
                case GREEN:
                    ((Fantastic) this.lastPlayedCard).setColor(Color.GREEN);
                    ((Fantastic) this.lastPlayedCard).setName("Fantastic: Green");
                    break;
                case YELLOW:
                    ((Fantastic) this.lastPlayedCard).setColor(Color.YELLOW);
                    ((Fantastic) this.lastPlayedCard).setName("Fantastic: Yellow");
                    break;
                case PURPLE:
                    ((Fantastic) this.lastPlayedCard).setColor(Color.PURPLE);
                    ((Fantastic) this.lastPlayedCard).setName("Fantastic: Purple");
                    break;
            }
        }
    }

    public boolean updateWish(Color color) {
        return true;
    }

    private void updateGamestate() {
        this.gameState.setPlayableColor(this.lastPlayedCard.getColor());
        this.gameState.setPlayableNumber(this.lastPlayedCard.getNumber());
        this.gameState.setLastCardName(this.lastPlayedCard.getName());
        this.gameState.setCards(new int[numberOfPlayers]);
        for (int i = 0; i < numberOfPlayers; i++){
            this.gameState.getCards()[i] = this.players.get(i).size();
        }
        connector.updateGamestate(this.gameState);
    }

    private Card drawCard() {
        if (!drawingPile.isEmpty()){
            drawingPile = CardDatabase.getShuffledCards();
        }
        return drawingPile.removeFirst();
    }
}

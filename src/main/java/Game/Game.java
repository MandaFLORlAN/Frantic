package Game;

import Cards.Card;
import Connector.Connector;
import Repository.CardDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Game{
    private List<Card> drawingPile;
    private List<Card> pile;
    private Card lastPlayedCard;
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

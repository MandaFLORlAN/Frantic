package Connector;

import Cards.Card;
import Cards.SpecialCard;
import ConsolePlayers.Player;
import ConsolePlayers.RandomBot;
import Enums.Color;
import Enums.FantasticOptions;
import Game.*;
import Repository.FranticConfigs;
import Statistics.StatisticsHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsoleConnector implements Connector {
    private Game game;
    private Map<String, Player> players;
    private StatisticsHandler statistics;

    public ConsoleConnector() {
        super();
    }


    @Override
    public void startGame(List<Player> players, StatisticsHandler statistics) throws InterruptedException {
        List<String> names = new ArrayList<>();
        this.players = new HashMap<>();
        for(Player p: players){
            this.players.put(p.getPlayerName(), p);
            names.add(p.getPlayerName());
        }
        this.statistics = statistics;
        this.game = new Game(names, this, FranticConfigs.NUMBER_OF_START_CARDS);
        for (Player player : players) {
            player.newRound();
        }
        this.game.startGame();
    }

    @Override
    public boolean addCardToPlayer(String playerName, Card card) {
        this.players.get(playerName).addCard(card.getName());
        return true;
    }

    @Override
    public void itsTurn(String playerName) {
        tellAllPlayers("its " + playerName + "'s turn");
        this.players.get(playerName).playMove();
    }

    @Override
    public void winners(List<String> winnerNames) {
        List<Player> winners = new ArrayList<>();
        for (String winnerName : winnerNames) {
            tellAllPlayers(winnerName + " has won!");
            winners.add(players.get(winnerName));
        }
        statistics.endGame(winners);
    }

    @Override
    public void updateGamestate(GameState gameState) {
        for (Player player : players.values()) {
            player.updateGamestate(gameState.toString());
        }
    }

    @Override
    public Color getPlayerColorWish(String playerName) {
        //TODO später
        return null;
    }

    @Override
    public boolean wantsToPlay(String playerName, String cardName) {
        Card card = Card.fromString(cardName);
        boolean canPlay = this.game.canPlay(playerName, card);
        if (canPlay) {
            if (card == null) {
                tellAllPlayers(playerName + " draws a card");
            } else {
                tellAllPlayers(playerName + " played " + card);
            }
        }
        return canPlay;
    }

    @Override
    public void executeSpecialFunction(String playerName, String cardName) {
        Card card = Card.fromString(cardName);
        if (card instanceof SpecialCard) {
            ((SpecialCard) card).executeSpecialFunction(playerName, this);
        }
    }

    @Override
    public void wishUpdate(String executorName, Color color) {
        //TOTO later for normal special cards
    }

    @Override
    public FantasticOptions getPlayerFantasticWish(String playerName) {
        return FantasticOptions.valueOf(this.players.get(playerName).fantasticWish());
    }

    @Override
    public void wishUpdate(String executorName, FantasticOptions fantasticOptions) {
        game.updateWish(this.players.get(executorName), fantasticOptions);
        tellAllPlayers("Wished for " + fantasticOptions.name());
    }

    @Override
    public String getPlayerTarget(String executorName, String message) {
        return players.get(executorName).getTarget(message);
    }

    @Override
    public List<Card> getCardToGiveAway(String playerName, int numberOfCards) {
        List<String> cardNamesToGiveAway = players.get(playerName).getCardsToGiveAway(numberOfCards);
        List<Card> cardsToGiveAway =  new ArrayList<>();
        for (String cardName : cardNamesToGiveAway) {
            cardsToGiveAway.add(Card.fromString(cardName));
        }
        return cardsToGiveAway;
    }

    @Override
    public List<Card> drawRandomCardFromPlayer(String playerName, int numberOfCards) {
        //TODO später
        return List.of();
    }

    @Override
    public void transferCardFromPlayerToPlayer(List<Card> cards, String giverName, String recieverName) {
        for (Card card : cards) {
            this.players.get(giverName).removeCard(card.getName());
            this.players.get(recieverName).addCard(card.getName(), "You got " + card.getName() + " from " + giverName);
        }
        tellAllPlayers(giverName + " transferred " + cards.size() + " cards to " + recieverName);
    }

    private void tellAllPlayers(String message) {
        for (Player player : players.values()) {
            player.updateGameActions(message);
            statistics.addMove(message);
        }
    }
}

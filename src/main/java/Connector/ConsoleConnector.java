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
    public boolean wantsToGiveAway(String playerName, String cardName) {
        //TODO später
        return false;
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
                if (card instanceof SpecialCard) {
                    ((SpecialCard) card).executeSpecialFunction(playerName, this);
                }
            }
        }
        return canPlay;
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
    public Player getPlayerTarget(String executorName, String message) {
        //TODO später
        return null;
    }

    @Override
    public Card getCardToGiveAway(String playerName) {
        //TODO später
        return null;
    }

    private void tellAllPlayers(String message) {
        for (Player player : players.values()) {
            player.updateGameActions(message);
            statistics.addMove(message);
        }
    }
}

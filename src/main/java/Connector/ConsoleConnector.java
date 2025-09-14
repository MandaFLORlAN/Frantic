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
        this.players.get(playerName).addCard(card);
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
            player.updateGamestate(gameState);
        }
    }

    @Override
    public boolean wantsToPlay(String playerName, Card card) {
        boolean canPlay = this.game.canPlay(playerName, card);
        if (canPlay) {
            if (card == null) {
                tellAllPlayers(playerName + " draws a card");
            } else {
                tellAllPlayers(playerName + " played " + card);
                if (card instanceof SpecialCard) {
                    ((SpecialCard) card).executeSpecialFunction(players.get(playerName), this);
                }
            }
        }
        return canPlay;
    }

    @Override
    public void wishUpdate(Player player, Color color) {
        //TOTO later for nomral special cards
    }

    @Override
    public void wishUpdate(Player player, FantasticOptions fantasticOptions) {
        game.updateWish(player, fantasticOptions);
        tellAllPlayers("Wished for " + fantasticOptions.name());
    }

    @Override
    public void updateGame() {
        //TODO wenn schenkkarten usw
    }

    private void tellAllPlayers(String message) {
        for (Player player : players.values()) {
            player.updateGameActions(message);
            statistics.addMove(message);
        }
    }
}

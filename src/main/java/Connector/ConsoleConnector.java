package Connector;

import Cards.Card;
import ConsolePlayers.Player;
import Game.*;
import Repository.FranticConstants;
import Statistics.StatisticsHandler;

import java.util.ArrayList;
import java.util.List;

public class ConsoleConnector implements Connector {
    private Game game;
    private List<Player> players;
    private StatisticsHandler statistics;

    public ConsoleConnector() {
        super();
    }


    @Override
    public void startGame(List<Player> players, StatisticsHandler statistics) throws InterruptedException {
        this.players = players;
        this.statistics = statistics;
        for (int i  = 0; i < players.size(); i++) {
            players.get(i).setPlayerId(i);
        }
        this.game = new Game(players.size(), this, FranticConstants.NUMBER_OF_START_CARDS);

        for (int i = 0; i<100_000; i++) {
            if (i%1000==0) System.out.println(i);
            for (Player player: players) {
                player.newRound();
            }
            this.game.startGame();
        }
        statistics.printStatistics();
    }

    @Override
    public boolean addCardToPlayer(int playerId, Card card) {
        players.get(playerId).addCard(card);
        return true;
    }

    @Override
    public void itsTurn(int playerId) {
        tellAllPlayers("its " + playerOfId(playerId) + "'s turn");
        players.get(playerId).playMove();
    }

    @Override
    public void winners(List<Integer> winnerIds) {
        List<Player> winners = new ArrayList<>();
        for (Integer playerID : winnerIds) {
            tellAllPlayers(players.get(playerID).getPlayerName() + " has won!");
            winners.add(players.get(playerID));
        }
        statistics.endGame(winners);
    }

    @Override
    public void updateGamestate(GameState gameState) {
        for (Player player : players) {
            player.updateGamestate(gameState);
        }
    }

    @Override
    public boolean wantsToPlay(int playerId, Card card) {
        boolean canPlay = this.game.canPlay(playerId, card);
        if (canPlay) {
            if (card == null) {
                tellAllPlayers(playerOfId(playerId) + " draws a card");
            } else {
                tellAllPlayers(playerOfId(playerId) + " played " + card);
            }
        }
        return canPlay;
    }

    @Override
    public void updateGame() {
        //TODO wenn schenkkarten usw
    }

    private String playerOfId(int playerId) {
        for (Player player: players){
            if (player.getPlayerId() == playerId) {
                return player.getPlayerName();
            }
        }
        return "Player: " + playerId;
    }

    private void tellAllPlayers(String message) {
        for (Player player: players) {
            player.updateGameActions(message);
            statistics.addMove(message);
        }
    }
}

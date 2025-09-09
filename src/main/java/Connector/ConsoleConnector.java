package Connector;

import Cards.Card;
import Cards.SpecialCard;
import ConsolePlayers.Player;
import Enums.Color;
import Enums.FantasticOptions;
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
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setPlayerId(i);
        }
        this.game = new Game(players.size(), this, FranticConstants.NUMBER_OF_START_CARDS);
        for (Player player : players) {
            player.newRound();
        }
        this.game.startGame();
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
                if (card instanceof SpecialCard) {
                    ((SpecialCard) card).executeSpecialFunction(players.get(playerId), this);
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

    private String playerOfId(int playerId) {
        for (Player player : players) {
            if (player.getPlayerId() == playerId) {
                return player.getPlayerName();
            }
        }
        return "Player: " + playerId;
    }

    private void tellAllPlayers(String message) {
        for (Player player : players) {
            player.updateGameActions(message);
            statistics.addMove(message);
        }
    }
}

package Statistics;

import Players.Player;

import java.util.List;

public class GameEndStats {
    private final List<Player> winners;
    private final int numberOfMoves;
    private final List<String> moves;

    public GameEndStats(List<Player> winners, int numberOfMoves, List<String> moves) {
        this.winners = winners;
        this.numberOfMoves = numberOfMoves;
        this.moves = moves;
    }

    public List<Player> getWinners() {
        return winners;
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public List<String> getMoves() {
        return moves;
    }
}

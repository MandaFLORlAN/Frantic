package ConsolePlayers;

import Cards.Card;
import Connector.Connector;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HumanPlayer extends Player {
    public HumanPlayer(String playerName, Connector connector) {
        super(playerName, connector);
    }

    @Override
    public void playMove() {
        displayGamestate();
        List<Card> playableCards = new ArrayList<>();
        System.out.println("Your cards are:");
        for (Card card : this.cards) {
            String cardLine = card.getName();
            if (card.isPlayable(this.gameState)) {
                cardLine += ":" + playableCards.size();
                playableCards.add(card);
            }
            System.out.println(cardLine);
        }
        System.out.println("witch card do you want to play? (" + playableCards.size() + " to draw)");
        int move = getIntInput(playableCards.size());
        if (move == playableCards.size()) {
            connector.wantsToPlay(this.playerId, null);
        } else {
            if (connector.wantsToPlay(this.playerId, playableCards.get(move))) {
                this.cards.remove(playableCards.get(move));
            }
        }
    }

    private int getIntInput(int max) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            int result;
            try {
                result = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Input must be a number. Please try again.");
                continue;
            }
            if (result > max ||  result < 0) {
                System.out.println("Number must be between 0 and " + max + ". Please try again.");
                continue;
            }
            return result;
        }
    }


    //View
    @Override
    public void updateGameActions(String message) {
        System.out.println(message);
    }

    private void displayGamestate() {
        for (int i = 0; i < this.gameState.getCards().length; i++) {
            if (i == playerId) {
                System.out.print("(you)");
            }
            System.out.println("Player:" + i + ": " + this.gameState.getCards()[i]);
        }
    }
}

package ConsolePlayers;

import Cards.Card;
import Connector.Connector;
import Enums.FantasticOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HumanPlayer extends RandomBot {
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
            connector.wantsToPlay(this.playerName, null);
        } else {
            if (connector.wantsToPlay(this.playerName, playableCards.get(move))) {
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
            if (result > max || result < 0) {
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

    @Override
    public FantasticOptions fantasticWish() {
        System.out.println("What fantastic option do you wish for.");
        FantasticOptions[] options = FantasticOptions.values();
        for (int i = 1; i <= options.length; i++) {
            System.out.println(options[i - 1].toString() + ": " + i);
        }
        int input = 0;
        while (input == 0) {
            input = getIntInput(options.length);
        }
        return options[input - 1];
    }

    private void displayGamestate() {
        for (String playerName : this.gameState.getCards().keySet()) {
            System.out.println(playerName + ": " + this.gameState.getCards().get(playerName));
        }
    }
}

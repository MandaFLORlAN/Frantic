package ConsolePlayers;

import Cards.BlackCard;
import Cards.Card;
import Cards.SpecialCard;
import Connector.Connector;
import Enums.Color;
import Enums.FantasticOptions;

import java.util.*;

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
            Card card = playableCards.get(move);
            if (connector.wantsToPlay(this.playerName, card.toString())) {
                this.cards.remove(card);
                connector.executeSpecialFunction(this.playerName, card.toString());
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

    @Override
    public List<String> getCardsToGiveAway(int numberOfCards) {
        if (numberOfCards > this.cards.size()) {
            numberOfCards = this.cards.size();
        }
        List<String> cardsToGiveAway = new ArrayList<>();
        List<Integer> indexesToGive = new ArrayList<>();
        for (int i = 0; i < this.cards.size(); i++) {
            System.out.println(cards.get(i).toString() + ": " + i);
        }
        if (numberOfCards == 1) {
            System.out.println("What card do you want to give away?");
        } else {
            System.out.println("What card's do you want to give away? (" + numberOfCards + ")");
        }
        while (indexesToGive.size() < numberOfCards) {
            Integer input = getIntInput(this.cards.size());
            if (indexesToGive.contains(input)) {
                System.out.println("Already chosen that card, give another");
            } else {
                indexesToGive.add(input);
            }
        }
        for (Integer index : indexesToGive) {
            cardsToGiveAway.add(cards.get(index).toString());
        }
        for (String card : cardsToGiveAway) {
            this.cards.remove(Card.fromString(card));
        }
        return cardsToGiveAway;
    }

    @Override
    public String getTarget(String message) {
        List<String> players = new ArrayList<>(this.gameState.getCards().keySet());
        for (String player: players) {
            if (player.equals(this.playerName)) {
                players.remove(player);
            }
        }
        for (int i = 0; i < players.size(); i++) {
            System.out.println(players.get(i) + ": " + i);
        }
        System.out.println("Who do you want to target with " + message);
        return players.get(getIntInput(players.size()));
    }


    //View
    @Override
    public void updateGameActions(String message) {
        System.out.println(message);
    }

    @Override
    public void addCard(String cardName, String message) {
        System.out.println(message);
        super.addCard(cardName);
    }

    @Override
    public String fantasticWish() {
        System.out.println("What fantastic option do you wish for.");
        FantasticOptions[] options = FantasticOptions.values();
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i].toString() + ": " + i);
        }
        int input = -1;
        while (input == -1) {
            input = getIntInput(options.length);
        }
        return options[input].toString();
    }

    private void displayGamestate() {
        sortCardsLikeMe();
        for (String playerName : this.gameState.getCards().keySet()) {
            System.out.println(playerName + ": " + this.gameState.getCards().get(playerName));
        }
        System.out.println("Last Played: " + this.gameState.getLastCardName());
    }

    private void sortCardsLikeMe() {
        List<Card> sortedCards = new ArrayList<>();

        List<Card> blackCards = new ArrayList<>();
        List<Card> specialCards = new ArrayList<>();
        List<Card> regularCards = new ArrayList<>();

        for (Card card : this.cards) {
            if (card instanceof BlackCard) {
                blackCards.add(card);
            }
            if (card instanceof SpecialCard) {
                specialCards.add(card);
            }
        }
        for (Card card : blackCards) {
            this.cards.remove(card);
        }
        for (Card card : specialCards) {
            this.cards.remove(card);
        }
        blackCards = sortByNumber(blackCards);
        specialCards = sortByColor(specialCards);
        regularCards = sortByColor(this.cards);

        sortedCards.addAll(blackCards);
        sortedCards.addAll(regularCards);
        sortedCards.addAll(specialCards);
        this.cards = sortedCards;
    }

    private List<Card> sortByNumber(List<Card> cardsToSort) {
        List<Card> sortedCards = new ArrayList<>();
        int currentSelector = 1;
        while (!cardsToSort.isEmpty()) {
            List<Card> newFoundCards = new ArrayList<>();
            for (Card card : cardsToSort) {
                if (card.getNumber() == currentSelector) {
                    sortedCards.add(card);
                    newFoundCards.add(card);
                }
            }
            for (Card card : newFoundCards) {
                cardsToSort.remove(card);
            }
            currentSelector++;
            if (currentSelector > 10) {
                sortedCards.addAll(cardsToSort);
                break;
            }
        }
        return sortedCards;
    }

    private List<Card> sortByColor(List<Card> cardsToSort) {
        List<Card> sortedCards = new ArrayList<>();
        for (Color color : Color.values()) {
            List<Card> cardsOfColor = new ArrayList<>();
            for (Card card : cardsToSort) {
                if (card.getColor() == color) {
                    cardsOfColor.add(card);
                }
            }
            cardsToSort.removeAll(cardsOfColor);
            cardsOfColor = sortByNumber(cardsOfColor);
            sortedCards.addAll(cardsOfColor);
        }
        sortedCards.addAll(cardsToSort);
        return sortedCards;
    }
}

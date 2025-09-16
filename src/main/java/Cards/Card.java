package Cards;

import Enums.Color;
import Game.GameState;
import Repository.CardDatabase;
import java.util.List;

public abstract class Card {
    protected String name;
    protected int number;
    protected Color color;

    public Card(String name, int number, Color color) {
        this.name = name;
        this.number = number;
        this.color = color;
    }

    public boolean isPlayable(GameState gs) {
        return false;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public Color getColor() {
        return color;
    }

    public static Card fromString(String cardName) {
        if (cardName == null) return null;
        if (cardName.startsWith("Fantastic")) {
            Fantastic fantastic = new Fantastic();
            fantastic.setName(cardName);
            return fantastic;
        }
        List<Card> allCards = CardDatabase.getAllCards();
        for (Card card : allCards) {
            if (card.getName().equals(cardName)) {
                return card;
            }
        }
        System.out.println("Card not found");
        return allCards.getFirst();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof Card) {
            Card card = (Card) obj;
            return card.getName().equals(this.name);
        }
        return false;
    }

    @Override
    public String toString() {
        return name;
    }
}

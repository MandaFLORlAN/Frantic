package Cards.InterfacesGroundclass;

import Enums.Color;
import Game.GameState;
import Repository.CardDatabase;

import java.util.List;
import java.util.Objects;

public abstract class Card {
    protected String name;
    protected int number;
    protected Color color;

    public Card(String name, int number, Color color) {
        this.name = name;
        this.number = number;
        this.color = color;
    }

    public boolean isPlayable(GameState gs, String executor) {
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
        List<Card> allCards = CardDatabase.getAllCards();
        for (Card card : allCards) {
            if (card.getName().equals(cardName)) {
                return card;
            }
            if (card instanceof WishCard) {
                String subname = cardName.split(": ")[0];
                boolean ram = Objects.equals(card.getName(), subname);
                if (ram) return card;
            }
        }
        System.out.println("Card not found for:" + cardName);
        return allCards.getFirst();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof Card) {
            Card card = (Card) obj;
            if (card instanceof WishCard) {
                return card.getName().split(": ")[0].equals(this.name.split(": ")[0]);
            }
            return card.getName().equals(this.name);
        }
        return false;
    }

    @Override
    public String toString() {
        return name;
    }
}

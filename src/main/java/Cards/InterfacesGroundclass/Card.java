package Cards.InterfacesGroundclass;

import Enums.Color;
import Game.GameState;
import Repository.CardDatabase;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public abstract class Card {
    protected String name;
    protected int number;
    protected Color color;
    protected int value;

    public Card(String name, int number, int value, Color color) {
        this.name = name;
        this.number = number;
        this.value = value;
        this.color = color;
    }

    public boolean isPlayable(GameState gs, String executor) {
        return false;
    }

    /*public abstract Card copyCard();*/

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public Color getColor() {
        return color;
    }

    public int getValue() {
        return value;
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

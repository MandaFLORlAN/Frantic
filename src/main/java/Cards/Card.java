package Cards;

import Enums.Color;
import Game.GameState;

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

    @Override
    public String toString() {
        return name;
    }
}

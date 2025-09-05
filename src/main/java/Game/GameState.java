package Game;

import Enums.Color;

public class GameState {
    private String lastCardName = "Black 1";
    private Color playableColor = null;
    private int playableNumber = 0;
    private int[] cards = {};

    public GameState() {}

    public Color getPlayableColor() {
        return playableColor;
    }

    public int getPlayableNumber() {
        return playableNumber;
    }

    public int[] getCards() {
        return cards;
    }

    public String getLastCardName() {
        return lastCardName;
    }

    public void setLastCardName(String lastCardName) {
        this.lastCardName = lastCardName;
    }

    public void setPlayableColor(Color playableColor) {
        this.playableColor = playableColor;
    }

    public void setCards(int[] cards) {
        this.cards = cards;
    }

    public void setPlayableNumber(int playableNumber) {
        this.playableNumber = playableNumber;
    }
}

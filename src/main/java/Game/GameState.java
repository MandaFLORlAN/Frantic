package Game;

import Cards.InterfacesGroundclass.Card;
import Enums.Color;

import java.util.HashMap;
import java.util.Map;

public class GameState {
    private Card lastCard = null;
    private Color playableColor = null;
    private int playableNumber = 0;
    private Map<String, Integer> cards = new HashMap<>();

    public GameState(Card lastCard, Color playableColor, int playableNumber, Map<String, Integer> cards) {
        this.lastCard = lastCard;
        this.playableColor = playableColor;
        this.playableNumber = playableNumber;
        this.cards = cards;
    }

    public GameState() {
    }

    public Color getPlayableColor() {
        return playableColor;
    }

    public int getPlayableNumber() {
        return playableNumber;
    }

    public Map<String, Integer> getCards() {
        return cards;
    }

    public Card getLastCard() {
        return lastCard;
    }

    public void setLastCard(Card lastCard) {
        this.lastCard = lastCard;
    }

    public void setPlayableColor(Color playableColor) {
        this.playableColor = playableColor;
    }

    public void setCards(Map<String, Integer> cards) {
        this.cards = cards;
    }

    public void setPlayableNumber(int playableNumber) {
        this.playableNumber = playableNumber;
    }

}

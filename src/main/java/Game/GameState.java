package Game;

import Enums.Color;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GameState {
    private String lastCardName = "Black 1";
    private Color playableColor = null;
    private int playableNumber = 0;
    private Map<String, Integer> cards = new HashMap<>();

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

    public String getLastCardName() {
        return lastCardName;
    }

    public void setLastCardName(String lastCardName) {
        this.lastCardName = lastCardName;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PlayableColor: " + playableColor + ",\n");
        sb.append("PlayableNumber: " + playableNumber + ",\n");
        for (String playerName : cards.keySet()) {
            sb.append(playerName + ": " + cards.get(playerName) + ",\n");
        }
        sb.append("Last played Card; " + lastCardName);//carefull its a ; to not split :
        return sb.toString();
    }

    public static GameState fromString(String str) {
        GameState gameState = new GameState();
        try {
            String[] values = str.split(",\n");
            String colorName = values[0].split(": ")[1];
            if (Objects.equals(colorName, "null")) gameState.setPlayableColor(null);
            else gameState.setPlayableColor(Color.valueOf(colorName));
            gameState.setPlayableNumber(Integer.parseInt(values[1].split(": ")[1]));
            Map<String, Integer> cards = new HashMap<>();
            for (int i = 2; i < values.length - 1; i++) {
                cards.put(values[i].split(": ")[0], Integer.parseInt(values[i].split(": ")[1]));
            }
            gameState.setCards(cards);
            gameState.setLastCardName(values[values.length - 1].split("; ")[1]);
        } catch (Exception e) {
            System.out.println("String has the wrong format to be converted to Gamestate");
        }
        return gameState;
    }
}

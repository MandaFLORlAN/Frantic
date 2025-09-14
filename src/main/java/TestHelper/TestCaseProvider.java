package TestHelper;

import Enums.Color;
import Game.GameState;

import java.util.HashMap;
import java.util.Map;

public class TestCaseProvider {
    private static Map<String, Integer> getExampleCardMap() {
        Map<String, Integer> cards = new HashMap<>();
        cards.put("Player:1", 5);
        cards.put("Player:2", 6);
        cards.put("Player:3", 7);
        return cards;
    }

    public static GameState getRedFive() {
        GameState gameState = new GameState();
        gameState.setLastCardName("Red: 5");
        gameState.setPlayableColor(Color.RED);
        gameState.setPlayableNumber(5);
        gameState.setCards(getExampleCardMap());
        return gameState;
    }

    public static GameState getGreenEight() {
        GameState gameState = new GameState();
        gameState.setLastCardName("Green: 8");
        gameState.setPlayableColor(Color.GREEN);
        gameState.setPlayableNumber(8);
        gameState.setCards(getExampleCardMap());
        return gameState;
    }

    public static GameState getNull() {
        GameState gameState = new GameState();
        gameState.setLastCardName("Fantastic: 6");
        gameState.setPlayableColor(null);
        gameState.setPlayableNumber(6);
        gameState.setCards(getExampleCardMap());
        return gameState;
    }

    public static GameState getZero() {
        GameState gameState = new GameState();
        gameState.setLastCardName("Fantastic: RED");
        gameState.setPlayableColor(Color.RED);
        gameState.setPlayableNumber(0);
        gameState.setCards(getExampleCardMap());
        return gameState;
    }

}

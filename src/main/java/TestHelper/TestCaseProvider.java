package TestHelper;

import Cards.ColloredSpecial.Exchange;
import Cards.ColloredSpecial.TROUBLEMAKER;
import Cards.InterfacesGroundclass.Card;
import Cards.NormalAndCurses.FuckYou;
import Cards.NormalAndCurses.RegularCard;
import Cards.Wishcards.Fantastic;
import Cards.Wishcards.FantasticFour;
import Cards.Wishcards.NiceTry;
import Connector.TestConnector;
import Enums.Color;
import Game.Game;
import Game.PointBasedGame;
import Game.GameState;
import Players.Player;
import Players.TestHelpingBot;
import Repository.CardDatabase;
import Repository.FranticConfigs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public static TestConnector getTestGame() {
        TestConnector connector = new TestConnector();

        Player threeCards = new TestHelpingBot("threeCards", connector);
        Player hasAll = new TestHelpingBot("hasAll", connector);
        Player fiveCards = new TestHelpingBot("fiveCards", connector);
        Player tenCards = new TestHelpingBot("tenCards", connector);

        List<String> playerNames = new ArrayList<>();
        playerNames.add(threeCards.getPlayerName());
        playerNames.add(hasAll.getPlayerName());
        playerNames.add(fiveCards.getPlayerName());
        playerNames.add(tenCards.getPlayerName());

        Game game = new Game(playerNames, connector);
        Map<String, Player> players = new HashMap<>();
        players.put("threeCards", threeCards);
        players.put("hasAll", hasAll);
        players.put("fiveCards", fiveCards);
        players.put("tenCards", tenCards);
        connector.setState(game, players, playerNames);

        List<Card> allCardsOnce = CardDatabase.getAllCardsOnce();
        for (Card card : allCardsOnce) {
            connector.addCardToPlayer("hasAll", card);
            game.addCardToPlayer("hasAll", card);
        }
        connector.addCardToPlayer("threeCards", new RegularCard(7, Color.RED));
        game.addCardToPlayer("threeCards", new RegularCard(7, Color.RED));
        connector.addCardToPlayer("threeCards", new Fantastic());
        game.addCardToPlayer("threeCards", new Fantastic());
        connector.addCardToPlayer("threeCards", new Fantastic());
        game.addCardToPlayer("threeCards", new Fantastic());


        connector.addCardToPlayer("fiveCards",new RegularCard(2,Color.GREEN));
        game.addCardToPlayer("fiveCards",new RegularCard(2,Color.GREEN));
        connector.addCardToPlayer("fiveCards",new TROUBLEMAKER(Color.GREEN));
        game.addCardToPlayer("fiveCards",new TROUBLEMAKER(Color.GREEN));
        connector.addCardToPlayer("fiveCards",new Fantastic());
        game.addCardToPlayer("fiveCards",new Fantastic());
        connector.addCardToPlayer("fiveCards",new RegularCard(7, Color.BLUE));
        game.addCardToPlayer("fiveCards",new RegularCard(7, Color.BLUE));
        connector.addCardToPlayer("fiveCards",new RegularCard(5, Color.PURPLE));
        game.addCardToPlayer("fiveCards",new RegularCard(5, Color.PURPLE));

        connector.addCardToPlayer("tenCards", new FuckYou());
        game.addCardToPlayer("tenCards", new FuckYou());
        connector.addCardToPlayer("tenCards", new RegularCard(1, Color.GREEN));
        game.addCardToPlayer("tenCards", new RegularCard(1, Color.GREEN));
        connector.addCardToPlayer("tenCards", new RegularCard(2, Color.GREEN));
        game.addCardToPlayer("tenCards", new RegularCard(2, Color.GREEN));
        connector.addCardToPlayer("tenCards", new RegularCard(3, Color.GREEN));
        game.addCardToPlayer("tenCards", new RegularCard(3, Color.GREEN));
        connector.addCardToPlayer("tenCards", new RegularCard(4, Color.GREEN));
        game.addCardToPlayer("tenCards", new RegularCard(4, Color.GREEN));
        connector.addCardToPlayer("tenCards", new RegularCard(4, Color.RED));
        game.addCardToPlayer("tenCards", new RegularCard(4, Color.RED));
        connector.addCardToPlayer("tenCards", new NiceTry());
        game.addCardToPlayer("tenCards", new NiceTry());
        connector.addCardToPlayer("tenCards", new Fantastic());
        game.addCardToPlayer("tenCards", new Fantastic());
        connector.addCardToPlayer("tenCards", new FantasticFour());
        game.addCardToPlayer("tenCards", new FantasticFour());
        connector.addCardToPlayer("tenCards", new Exchange(Color.YELLOW));
        game.addCardToPlayer("tenCards", new Exchange(Color.YELLOW));

        game.addCardOnPile(new RegularCard(5, Color.RED));
        Map<String, Integer> cards = new HashMap<>();
        cards.put("threeCards", 3);
        cards.put("hasAll", CardDatabase.UNIQUE_CARDS);
        cards.put("fiveCards", 5);
        cards.put("tenCards", 10);
        game.getGameState().setCards(cards);
        connector.updateGamestate(game.getGameState());
        return connector;
    }
}

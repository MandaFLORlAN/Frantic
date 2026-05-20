package EventTests;

import Cards.NormalAndCurses.RegularCard;
import Connector.TestConnector;
import Enums.Color;
import Events.Basegame.Market;
import Players.Player;
import TestHelper.TestCaseProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MarketTest {
    @Test
    public void TestEventExecution() {
        TestConnector connector = TestCaseProvider.getTestGame();
        Player threeCards = connector.getPlayers().get("threeCards");
        Player hasAll = connector.getPlayers().get("hasAll");
        Player fiveCards = connector.getPlayers().get("fiveCards");
        Player tenCards = connector.getPlayers().get("tenCards");

        hasAll.getCards().remove(new RegularCard(1, Color.BLUE));

        connector.triggerEvent("threeCards", new Market());

        assertTrue(threeCards.getCards().contains(new RegularCard(2, Color.BLUE)));
        assertTrue(hasAll.getCards().contains(new RegularCard(3,Color.BLUE)));
        assertTrue(fiveCards.getCards().contains(new RegularCard(4,Color.BLUE)));
        assertTrue(tenCards.getCards().contains(new RegularCard(5,Color.BLUE)));
    }
}

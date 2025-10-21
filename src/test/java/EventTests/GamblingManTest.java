package EventTests;

import Cards.NormalAndCurses.FuckYou;
import Cards.NormalAndCurses.RegularCard;
import Connector.TestConnector;
import Enums.Color;
import Events.Basegame.GamblingMan;
import Players.Player;
import Repository.CardDatabase;
import TestHelper.TestCaseProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GamblingManTest {
    @Test
    public void TestEventExecution() {
        TestConnector connector = TestCaseProvider.getTestGame();
        Player threeCards = connector.getPlayers().get("threeCards");
        Player hasAll = connector.getPlayers().get("hasAll");
        Player fiveCards = connector.getPlayers().get("fiveCards");
        Player tenCards = connector.getPlayers().get("tenCards");

        connector.triggerEvent("threeCards", new GamblingMan());

        assertEquals(2,threeCards.getCards().size());
        assertEquals(CardDatabase.UNIQUE_CARDS-1,hasAll.getCards().size());
        assertEquals(8,fiveCards.getCards().size());
        assertEquals(9,tenCards.getCards().size());

        assertTrue(fiveCards.getCards().contains(new RegularCard(1, Color.BLUE)));
        assertTrue(fiveCards.getCards().contains(new RegularCard(7, Color.RED)));
        assertTrue(fiveCards.getCards().contains(new RegularCard(2, Color.GREEN)));
        assertTrue(fiveCards.getCards().contains(new FuckYou()));
    }
}
